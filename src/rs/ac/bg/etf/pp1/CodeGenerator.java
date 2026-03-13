package rs.ac.bg.etf.pp1;

import java.util.Stack;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;

public class CodeGenerator extends VisitorAdaptor {
	
	private int mainPc;
	
	/**
	 * Podsetnik:
	 * Code.put(0);	- U kod memoriju ubaci nulu u COMPILE TIME!
	 * Code.loadConst(0); - Pravi instrukciju const_0 koja kada se izvrsi u RUN TIME na expresion steku stavlja nulu!
	 * */
	private Obj lenMethod;
	
	private void initializePredeclaredMethods() {
        // 'ord' and 'chr' are the same code.
        Obj ordMethod = Tab.find("ord");
        Obj chrMethod = Tab.find("chr");
        ordMethod.setAdr(Code.pc);
        chrMethod.setAdr(Code.pc);
        Code.put(Code.enter);
        Code.put(1);
        Code.put(1);
        Code.put(Code.load_n);
        Code.put(Code.exit);
        Code.put(Code.return_);
 
        lenMethod = Tab.find("len");
        lenMethod.setAdr(Code.pc);
        Code.put(Code.enter);
        Code.put(1);
        Code.put(1);
        Code.put(Code.load_n);
        Code.put(Code.arraylength);
        Code.put(Code.exit);
        Code.put(Code.return_);
    }
	
	CodeGenerator() {
		this.initializePredeclaredMethods();
	}
	
	public int getMainPc() {
		return mainPc;
	}
	
	/*Method*/
	@Override
	public void visit(MethodRetName_void methodRetName_void) {
		methodRetName_void.obj.setAdr(Code.pc);
		if(methodRetName_void.getI1().equalsIgnoreCase("main"))
			this.mainPc = Code.pc;
		//enter instrukcija je obavezna na ulazu u metodu, enter instrukcija ima 3 bajta
		Code.put(Code.enter);										//U code memoriju putujemo jedan bajt - tj. op. code od enter instrukcije
		Code.put(methodRetName_void.obj.getLevel());				//b1, broj formalnih parametara
		Code.put(methodRetName_void.obj.getLocalSymbols().size());	//b2, ukupan broj lokalnih i formalnih
	}
	
	@Override
	public void visit(MethodRetName_type methodRetName_type) {
		methodRetName_type.obj.setAdr(Code.pc);
		if(methodRetName_type.getI2().equalsIgnoreCase("main"))
			this.mainPc = Code.pc;
		//enter instrukcija je obavezna na ulazu u metodu, enter instrukcija ima 3 bajta
		Code.put(Code.enter);										//U code memoriju putujemo jedan bajt - tj. op. code od enter instrukcije
		Code.put(methodRetName_type.obj.getLevel());				//b1, broj formalnih parametara
		Code.put(methodRetName_type.obj.getLocalSymbols().size());	//b2, ukupan broj lokalnih i formalnih
	}
	
	@Override
	public void visit(MethodDecl methodDecl) {
		Code.put(Code.exit);				//po izlazu iz metode prvo ide exit
		Code.put(Code.return_);				//pa return
	}
	
	/*Statements*/
	@Override
	public void visit(Statement_print1 statement_print1) {
		Code.loadConst(0);
		if(statement_print1.getExpr().struct.equals(Tab.charType))
			Code.put(Code.bprint);
		else
			Code.put(Code.print);
	}
	
	@Override
	public void visit(Statement_print2 statement_print2) {
		Code.loadConst(statement_print2.getConstantNUM().getN1());
		if(statement_print2.getExpr().struct.equals(Tab.charType))
			Code.put(Code.bprint);
		else
			Code.put(Code.print);
	}
	
	@Override
	public void visit(Statement_return1 statement_return1) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	@Override
	public void visit(Statement_return2 statement_return2) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	@Override
	public void visit(Statement_read statement_read) {
		if(statement_read.getDesignator().obj.getType().equals(Tab.charType))
			Code.put(Code.bread);
		else
			Code.put(Code.read);
		
		Code.store(statement_read.getDesignator().obj);
	}
	
	//Sve su stekovi zbog potencijalnog ugnjezdavanja switcheva!		
	private Stack<Stack<Integer>> breakPC = new Stack<Stack<Integer>>();
	private Stack<Stack<Integer>> skipCase = new Stack<Stack<Integer>>();
	private int skipCaseNum = -1;
	
	/*Resiti ovaj problem oko generisanja skoka!*/
	@Override
	public void visit(CaseNum caseNum) {
		Code.put(Code.dup);
		Code.loadConst(caseNum.getConstantNUM().getN1());
		Code.putFalseJump(Code.eq, 0);	//ukoliko nisu jednake vrednosti na steku, moram da skocim, generise se jne 0
		skipCase.peek().push(Code.pc - 2);
		//posle ovoga se desava kod ukoliko sam usao u case
		
		//Code.loadConst(0);		//0 koja oznacava da se nije desio break
		if(skipCaseNum != -1)
			Code.fixup(skipCaseNum);
		
	}
	
	@Override
	public void visit(Statement_switch statement_switch) {
		skipCase.pop();
		while(!breakPC.peek().empty())
			Code.fixup(breakPC.peek().pop());
		//Logika za continue - pocetak
		Code.loadConst(0);
		Code.putJump(0);
		int skip = Code.pc - 2;
		
		if(continuePC != null && !continuePC.empty()) {
			while(!continuePC.peek().empty()) 
				Code.fixup(continuePC.peek().pop());
			Code.put(Code.pop);
			Code.loadConst(1);
		}
		Code.fixup(skip);
		Code.loadConst(1);
		Code.putFalseJump(Code.ne, 0);
		if(continuePC != null && !continuePC.empty()) {
			continuePC.peek().push(Code.pc - 2);
		}
		//Logika za continue - kraj
		Code.put(Code.pop);
		breakPC.pop();
	}
	
	@Override
	public void visit(Statement_br statement_br) {
		Code.putJump(0);
		breakPC.peek().push(Code.pc - 2);
	}
	
	@Override
	public void visit(Cases cases) {
		if(skipCaseNum != -1)
			Code.fixup(skipCaseNum);
	}
	
	@Override
	public void visit(Case case_) {
		/*if(!breakPC.peek().empty())
			Code.fixup(breakPC.peek().peek());*/
		/*Code.loadConst(0);
		Code.loadConst(1);*/
		Code.putJump(0);				//ovaj kod ce se izvrsiti SAMO AKO SE BREAK NIJE DESIO U RUNTIMEU, PA ONDA IDEMO FALL THROUGH
		skipCaseNum = Code.pc - 2;
		while(!skipCase.peek().empty()) 
			Code.fixup(skipCase.peek().pop());
		//Code.fixup(skipCaseNum);
	}
	
	@Override
	public void visit(NonTerminalSwitch nonTerminalSwitch) {
		skipCase.push(new Stack<Integer>());
		breakPC.push(new Stack<Integer>());
		//continuePC.push(new Stack<Integer>());
	}
	
	/*For*/
	private Stack<Integer> forCndStart = new Stack<Integer>();
	private Stack<Stack<Integer>> mayDesg2PC = new Stack<Stack<Integer>>();
	private Stack<Stack<Integer>> continuePC = new Stack<Stack<Integer>>();
	
	@Override
	public void visit(MayDesg1_yes mayDesg1_yes) {
		forCndStart.push(Code.pc);
	}
	
	@Override
	public void visit(MayDesg1_eps mayDesg1_eps) {
		forCndStart.push(Code.pc);
	}
	
	@Override
	public void visit(MayCond_yes mayCond_yes) {
		Code.putJump(0);
		mayDesg2PC.peek().push(Code.pc - 2);
	}
	
	@Override
	public void visit(MayCond_eps mayCond_eps) {
		Code.putJump(0);
		mayDesg2PC.peek().push(Code.pc - 2);
	}
	
	@Override
	public void visit(ForStatementStart forStatementStart) {
		//Code.loadConst(0);
		Code.fixup(mayDesg2PC.peek().peek());
	}
	
	@Override
	public void visit(NonTerminalFor nonTerminalFor) {
		breakPC.push(new Stack<Integer>());
		continuePC.push(new Stack<Integer>());
		mayDesg2PC.push(new Stack<Integer>());
	}
	
	@Override
	public void visit(MayDesg2_eps mayDesg2_eps) {
		Code.putJump(forCndStart.peek());
	}
	
	@Override
	public void visit(MayDesg2_yes mayDesg2_yes) {
		Code.putJump(forCndStart.peek());
	}
	
	@Override
	public void visit(ForStatement forStatement) {
		while(!continuePC.peek().empty()) {
			Code.fixup(continuePC.peek().pop());
			//Code.put(Code.pop);
		}
		/*while(!breakPC.peek().empty())
			Code.fixup(breakPC.peek().pop());*/
		
		//Code.put(Code.pop);
		Code.putJump(mayDesg2PC.peek().pop() + 2);
	}
	
	@Override
	public void visit(Statement_for statement_for) {
		while(!breakPC.peek().empty())
			Code.fixup(breakPC.peek().pop());
		//Code.put(Code.pop);
		Code.fixup(skipThen.pop());
		
		breakPC.pop();
		continuePC.pop();
		mayDesg2PC.pop();
		forCndStart.pop();
	}
	
	/*Continue*/
	public void visit(Statement_continue statement_continue) {
		Code.putJump(0);
		continuePC.peek().push(Code.pc - 2);
	}
	
	/*Factor*/
	@Override
	public void visit(Factor factor) {
		if(factor.getUnary() instanceof Unary_minus)
			Code.put(Code.neg);
	}
	
	/*FactorSingle*/
	@Override
	public void visit(FactorSingle_newArr factorSingle_newArr) {
		Code.put(Code.newarray);
		if(factorSingle_newArr.getType().struct.equals(Tab.intType))
			Code.put(1);			//kreira se niz sa recima duzim od bajta				
		else
			Code.put(0);			//niz sa elemntima velicine bajta
	}
	
	@Override
	public void visit(FactorSingle_method factorSingle_method) {
		int off = factorSingle_method.getDesignator().obj.getAdr() - Code.pc;		//offset mora da se izracuna pre Code.call!
		Code.put(Code.call);
		Code.put2(off);
	}
	
	@Override
	public void visit(FactorSingle_num factorSingle_num) {
		Code.loadConst(factorSingle_num.getN1());
	}
	
	@Override
	public void visit(FactorSingle_char factorSingle_char) {
		Code.loadConst(factorSingle_char.getC1());
	}
	
	@Override
	public void visit(FactorSingle_bool factorSingle_bool) {
		Code.loadConst(factorSingle_bool.getB1());
	}
	
	@Override
	public void visit(FactorSingle_var factorSingle_var) {
		if(!factorSingle_var.getDesignator().obj.getName().equals("LENGTH")) {
			Obj degObj = factorSingle_var.getDesignator().obj;
			Code.load(degObj);
		}
	}
	
	/*Addop Term*/
	@Override
	public void visit(AddopTermList_list addopTermList_list) {
		if(addopTermList_list.getAddop() instanceof AddOP_PLUS)
			Code.put(Code.add);
		else if(addopTermList_list.getAddop() instanceof AddOP_MINUS)
			Code.put(Code.sub);
	}
	
	/*Mulop Term*/
	@Override
	public void visit(Term_mulfactor term_mulfactor) {
		if(term_mulfactor.getMulop() instanceof Mulop_MUL)
			Code.put(Code.mul);
		else if(term_mulfactor.getMulop() instanceof Mulop_DIV)
			Code.put(Code.div);
		else if(term_mulfactor.getMulop() instanceof Mulop_MOD)
			Code.put(Code.rem);
	}
	
	/*Designator*/
	@Override
	public void visit(DesignatorName designatorName) {
		if(designatorName.obj.getKind() == Obj.Var || designatorName.obj.getKind() == Obj.Elem)
			Code.load(designatorName.obj);
	}
	
	@Override
	public void visit(DesignatorArrEnum designatorArrEnum) {
		if(designatorArrEnum.getDesignatorKind() instanceof DesignatorKind_len) {
			int off = lenMethod.getAdr() - Code.pc;
			Code.put(Code.call);
			Code.put2(off);
		}
	}
	
	/*DesignatorStatement*/
	@Override
	public void visit(DesignatorStatement_assign designatorStatement_assign) {
		Code.store(designatorStatement_assign.getDesignator().obj);
	}
	
	@Override
	public void visit(DesignatorStatement_inc designatorStatement_inc) {
		if(designatorStatement_inc.getDesignator().obj.getKind() == Obj.Elem)
			Code.put(Code.dup2);
		Code.load(designatorStatement_inc.getDesignator().obj);
		Code.loadConst(1);
		Code.put(Code.add);
		Code.store(designatorStatement_inc.getDesignator().obj);
	}
	
	@Override
	public void visit(DesignatorStatement_dec designatorStatement_dec) {
		if(designatorStatement_dec.getDesignator().obj.getKind() == Obj.Elem)
			Code.put(Code.dup2);
		Code.load(designatorStatement_dec.getDesignator().obj);
		Code.loadConst(1);
		Code.put(Code.sub);
		Code.store(designatorStatement_dec.getDesignator().obj);
	}
	
	@Override
	public void visit(DesignatorStatement_meth designatorStatement_meth) {
		int off = designatorStatement_meth.getDesignator().obj.getAdr() - Code.pc;		//offset mora da se izracuna pre Code.call!
		Code.put(Code.call);
		Code.put2(off);
		
		if(designatorStatement_meth.getDesignator().obj.getType() != Tab.noType)
			Code.put(Code.pop);
	}
	
	/*Conditions*/
	
	private Stack<Integer> skipCondFact = new Stack<>();
	private Stack<Integer> skipCondition = new Stack<>();
	private Stack<Integer> skipThen = new Stack<>();
	private Stack<Integer> skipElse = new Stack<>();
	
	private int returnRelop(Relop relop) {
		
		if(relop instanceof RelOP_eq)
			return Code.eq;
		else if(relop instanceof RelOP_noeq)
			return Code.ne;
		else if(relop instanceof RelOP_grater)
			return Code.gt;
		else if(relop instanceof RelOP_less)
			return Code.lt;
		else if(relop instanceof RelOP_gratereq)
			return Code.ge;
		else if(relop instanceof RelOP_leseq)
			return Code.le;
		else
			return 0;
	}
	
	@Override
	public void visit(CondFact_expr condFact_expr) {
		Code.loadConst(0);					//ucitavamo nulu da bi imali sa cim da poredimo, ukoliko je na steku iz Expr-a ostala nula, condition nije ispunjen pa se skace
		Code.putFalseJump(Code.ne, 0); 		//Ovo u prevodu znaci skacem ukoliko su jednaki tj. ukoliko condition nije ispunjen, idalje ne znam gde skacem
		skipCondFact.push(Code.pc - 2);
		//ukoliko je condition tacan:
	}
	
	@Override
	public void visit(CondFact_rel condFact_rel) {
		Code.putFalseJump(returnRelop(condFact_rel.getRelop()), 0); 		//Ovo u prevodu znaci skacem ukoliko su jednaki tj. ukoliko condition nije ispunjen, idalje ne znam gde skacem
		skipCondFact.push(Code.pc - 2);
	}
	
	@Override
	public void visit(CondTerm condTerm) {
		//ako smo dosli do ovde znaci da smo je bar jedan OR tacan i radimo bezuslovni skok na then deo
		Code.putJump(0);
		skipCondition.push(Code.pc - 2);
		while(!skipCondFact.empty()) 
			Code.fixup(skipCondFact.pop());
		//netacne nastavljaju na sledeci or
	}
	
	@Override
	public void visit(Condition condition) {
		//netacno
		Code.putJump(0); 	//netacni idu na ELSE
		skipThen.push(Code.pc - 2);
		//THEN
		while(!skipCondition.empty()) 
			Code.fixup(skipCondition.pop());
		//tacno
	}
	
	@Override
	public void visit(ElseStatement_eps elseStatement_eps) {
		Code.fixup(skipThen.pop());
	}
	
	@Override
	public void visit(Else else_) {
		Code.putJump(0); 		//tacne ne rade ELSE
		skipElse.push(Code.pc - 2);
		Code.fixup(skipThen.pop()); 	//netacne
	}
	
	@Override
	public void visit(ElseStatement_else elseStatement_else) {
		//netacne
		Code.fixup(skipElse.pop());	//ovde je ELSE vec obidjen tkd. gotov je, tu se upisuje adresa skoka za tacne conditione
		//ovde se desava i tacno i netacno
	}
	
	/*Ternani*/
	@Override
	public void visit(TernaniTrue ternaniTrue) {
		Code.putJump(0); 				//tacne ne rade ELSE
		skipElse.push(Code.pc - 2);
		Code.fixup(skipThen.pop()); 	//netacne
	}
	
	@Override
	public void visit(TernaniFalse ternaniFalse) {
		//netacne
		Code.fixup(skipElse.pop());	//ovde je ELSE vec obidjen tkd. gotov je, tu se upisuje adresa skoka za tacne conditione
		//ovde se desava i tacno i netacno
	}
	
	/**
	 * TODO:
	 * 	-Uraditi break za for;
	 * */
}