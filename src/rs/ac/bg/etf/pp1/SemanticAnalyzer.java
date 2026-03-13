package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticAnalyzer extends VisitorAdaptor {
	
	private boolean errorDetected = false;
	Logger log = Logger.getLogger(getClass());
	private Obj currProgram;
	private Struct currType;			//trenutni tip promenljive
	private int constant;
	private Struct constantType;		//constant Type je tip konstante npr. 2 -> int, 'a' - > char
	private Obj mainMethod = null;
	
	private Struct boolType = Tab.find("bool").getType();
	private Obj currentMethod = null;
	private Struct currentEnum = null;
	private boolean returnHappend;
	private int switchCnt = 0;
	private int forCnt = 0;
	private Stack <ArrayList<Integer>> caseNums = new Stack<ArrayList<Integer>>();
	int nVars;
	
	/*LOG Messages*/
	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if(line != 0)
			msg.append(" na liniji ").append(line);
		log.error(msg.toString());
		
	}
	
	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if(line != 0)
			msg.append(" na liniji ").append(line);
		log.info(msg.toString());
	}
	
	public boolean passed() { return !errorDetected; }
	
	/*Analyzer code*/
	@Override
	public void visit(Program_prog prog) {
		nVars = Tab.currentScope().getnVars();
		
		Tab.chainLocalSymbols(currProgram);
		Tab.closeScope();
		currProgram = null;
		
		if(mainMethod == null || mainMethod.getLevel() > 0)
			report_error("Program nema main metodu!", prog);
	}
	
	@Override
	public void visit(ProgramName progName) {
		currProgram = Tab.insert(Obj.Prog, progName.getI1(), Tab.noType);
		Tab.openScope();
	}
	
	/*Const declarations*/
	@Override
	public void visit(ConstDeclFinal constDeclFinal) {
		Obj constObj = Tab.find(constDeclFinal.getI1());
		if(constObj != Tab.noObj) {
			report_error("Dvostruka definicja konstante: " + constDeclFinal.getI1(), constDeclFinal);
		}
		else {
			if(constantType.assignableTo(currType)) {			//vrednost se dodeljuje samo konstantama
				constObj = Tab.insert(Obj.Con, constDeclFinal.getI1(), currType);
				constObj.setAdr(constant);
			} else {
				report_error("Neadekvatna dodela konstante: " + constDeclFinal.getI1(), constDeclFinal);
			}
		}
	}
	
	public void visit(ConstantNUM constNum) {
		constant = constNum.getN1();
		constantType = Tab.intType;
	}
	
	public void visit(ConstantCHAR constChar) {
		constant = constChar.getC1();
		constantType = Tab.charType;
	}
	
	public void visit(ConstantBOOL constBool) {
		constant = constBool.getB1();
		constantType = boolType;
	}
	
	/*Var declarations*/
	@Override
	public void visit(VarArray_var var) {
		Obj varObj = null;
		
		if(currentMethod == null)
			varObj = Tab.find(var.getI1());
		else
			varObj = Tab.currentScope().findSymbol(var.getI1());
			
		if(varObj == null || varObj == Tab.noObj) {
			varObj = Tab.insert(Obj.Var, var.getI1(), currType);
		}
		else {
			report_error("Dvostruka definicja konstante: " + var.getI1(), var);
		}
	}
	
	@Override
	public void visit(VarArray_array array) {
		Obj varObj = null;
		
		if(currentMethod == null)
			varObj = Tab.find(array.getI1());
		else
			varObj = Tab.currentScope().findSymbol(array.getI1());
			
		if(varObj == null || varObj == Tab.noObj) {
			varObj = Tab.insert(Obj.Var, array.getI1(), new Struct(Struct.Array, currType));
		}
		else {
			report_error("Dvostruka definicja konstante: " + array.getI1(), array);
		}
	}
	
	/*Enum declarations*/
	@Override
	public void visit(EnumDeclName enumName) {
		Obj enumObj = Tab.find(enumName.getI1());
		if(enumObj != Tab.noObj) {
			report_error("Dvostruka definicja enuma: " + enumName.getI1(), enumName);
		}
		else {
			currentEnum = new Struct(Struct.Enum);
			enumObj = Tab.insert(Obj.Type, enumName.getI1(), currentEnum);		//jedan enum cuvam kao const, gde type polje ukazuje na struct cvor tipa enum
			Tab.openScope();  					//otvara se scope za enum
		}
	}
	
	@Override
	public void visit(EnumDeclOne_decl enumDecl) {
		Obj enumObj = null;
		if(currentEnum == null)
			enumObj = Tab.find(enumDecl.getI1());
		else
			enumObj = Tab.currentScope().findSymbol(enumDecl.getI1());
		/*kada je samo deklaracija bez dodele vrednosti*/
		if(enumObj == Tab.noObj || enumObj == null) {
			enumObj = Tab.insert(Obj.Con, enumDecl.getI1(), Tab.intType);
			enumObj.setLevel(2);
			enumObj.setAdr(0);
		}
		else {
			report_error("Dvostruka definicja polja u enumu: " + enumDecl.getI1(), enumDecl);
		}
	}
	
	private Obj enumFieldNull;			//polje enuma kom je eksplicitno dodeljena vrednost nula
	
	@Override
	public void visit(EnumDeclOne_init enumInit) {
		/*kada je sa dodelom vrednosti*/
		Obj enumObj = null;
		if(currentEnum == null)
			enumObj = Tab.find(enumInit.getI1());
		else
			enumObj = Tab.currentScope().findSymbol(enumInit.getI1());
		
		if(enumObj == Tab.noObj || enumObj == null) {
			if(constantType.assignableTo(Tab.intType)) {			//vrednost se dodeljuje samo konstantama
				enumObj = Tab.insert(Obj.Con, enumInit.getI1() , Tab.intType);
				enumObj.setLevel(2);
				enumObj.setAdr(constant);
				if(constant == 0)
					enumFieldNull = enumObj;
			} else 
				report_error("Neadekvatna dodela konstante enumu: " + enumInit.getI1(), enumInit);
		}
		else {
			report_error("Dvostruka definicja polja u enumu: " + enumInit.getI1(), enumInit);
		}
	}
	
	@Override
	public void visit(EnumDecl enumDecl) {
		//treba zatvoriti scope
		Tab.chainLocalSymbols(currentEnum);
		currentEnum.setMembers(Tab.currentScope().getLocals());
		Tab.closeScope();
		ArrayList<Integer> values = new ArrayList<Integer>();
		for (Obj iterable_element :  currentEnum.getMembers()) {
			int value;
			
			if(iterable_element.getAdr() != 0)
				value = iterable_element.getAdr();
			else if(values.size() == 0 || iterable_element == enumFieldNull)
				value = 0;
			else
				value = values.get(values.size() - 1) + 1;
			
			if(values.contains(value)){
				report_error("Dvostruka dodela iste vrednosti u enumu [EnumDecl]: " + enumDecl.getEnumDeclName() + " \nvrednost: " + value, enumDecl);
			}
			else {
				iterable_element.setAdr(value);
				values.add(iterable_element.getAdr());
			}
		}
		
		currentEnum = null;
	}
	
	/*Method declarations*/
	@Override
	public void visit(MethodRetName_void methVoid) {
		if(methVoid.getI1().equalsIgnoreCase("main"))
			currentMethod = mainMethod = Tab.insert(Obj.Meth, methVoid.getI1(), Tab.noType);
		else
			currentMethod = Tab.insert(Obj.Meth, methVoid.getI1(), Tab.noType);
		methVoid.obj = currentMethod;
		Tab.openScope();
	}
	
	@Override
	public void visit(MethodRetName_type methType) {
		currentMethod = Tab.insert(Obj.Meth, methType.getI2(), currType);
		methType.obj = currentMethod;
		Tab.openScope();
	}
	
	@Override
	public void visit(MethodDecl method) {
		Tab.chainLocalSymbols(currentMethod);
		Tab.closeScope();
		if(currentMethod.getType() != Tab.noType && !returnHappend)
			report_error("Ne postoji return unutar metode: " + currentMethod.getName(), method);
		currentMethod = null;
		returnHappend = false;
	}
	
	/*Form params declarations*/
	@Override
	public void visit(FormParsVarArray_var form_var) {
		Obj varObj = null;
		
		if(currentMethod == null || mainMethod == currentMethod)
			report_error("Semanticka greska. [FormParsVarArray_var]", form_var);
		else
			varObj = Tab.currentScope().findSymbol(form_var.getI2());
			
		
		if(varObj == null || varObj == Tab.noObj) {
			varObj = Tab.insert(Obj.Var, form_var.getI2(), currType);
			varObj.setFpPos(1);
			currentMethod.setLevel(currentMethod.getLevel() + 1);
		}
		else {
			report_error("Dvostruka definicja formalnog parametra: " + form_var.getI2(), form_var);
		}
	}
	
	@Override
	public void visit(FormParsVarArray_array form_array) {
		Obj varObj = null;
		
		if(currentMethod == null)
			report_error("Semanticka greska. [FormParsVarArray_array]", form_array);
		else
			varObj = Tab.currentScope().findSymbol(form_array.getI2());
			
		
		if(varObj == null || varObj == Tab.noObj) {
			varObj = Tab.insert(Obj.Var, form_array.getI2(), new Struct(Struct.Array, currType));
			varObj.setFpPos(1);
			currentMethod.setLevel(currentMethod.getLevel() + 1);
		}
		else {
			report_error("Dvostruka definicja formalnog parametra: " + form_array.getI2(), form_array);
		}
	}
	
	@Override
	public void visit(Type type) {
		Obj typeObj = Tab.find(type.getI1());
		if(typeObj == Tab.noObj) {
			report_error("Nepostojeci tip podatka: " + type.getI1(), type);
			type.struct = currType = Tab.noType;
		}
		else if(typeObj.getKind() != Obj.Type) {
			report_error("Neadekvatan tip podatka" + type.getI1(), type);
			type.struct = currType = Tab.noType;
		}
		else {
			if(typeObj.getType().getKind() == Struct.Enum)
				type.struct = currType = Tab.intType;
			else
				type.struct = currType = typeObj.getType();
		}
			
	}
	
	/*Context conditions*/
	
	/*FactorSingle*/
	@Override
	public void visit(FactorSingle_newArr factorSignle_newArr) {
		if(!factorSignle_newArr.getExpr().struct.equals(Tab.intType)) {
			report_error("Pogresna velicina niza!", factorSignle_newArr);
			factorSignle_newArr.struct = Tab.noType;
		} else {
			factorSignle_newArr.struct = new Struct(Struct.Array, currType);
		}
	}
	
	@Override
	public void visit(FactorSingle_expr factorSignle_expr) {
		factorSignle_expr.struct = factorSignle_expr.getExpr().struct;
	}
	
	@Override
	public void visit(FactorSingle_char factorSingle_char) {
		factorSingle_char.struct = Tab.charType;
	}
	
	@Override
	public void visit(FactorSingle_num factorSignle_num) {
		factorSignle_num.struct = Tab.intType;
	}
	
	@Override
	public void visit(FactorSingle_bool factorSignle_bool) {
		factorSignle_bool.struct = boolType;
	}
	
	@Override
	public void visit(FactorSingle_var factorSignle_var) {
		if (factorSignle_var.getDesignator().obj != Tab.noObj && factorSignle_var.getDesignator().obj.getKind() != Obj.Meth) 
			factorSignle_var.struct = factorSignle_var.getDesignator().obj.getType();
		else
			factorSignle_var.struct = Tab.noType;
	}

	@Override
	public void visit(FactorSingle_method factorSignle_method) {
		if(factorSignle_method.getDesignator().obj.getKind() != Obj.Meth || factorSignle_method.getDesignator().obj.getName().equals("main")) {
			report_error("Poziv neadekvatne metode[FactorSingle_method]: " + factorSignle_method.getDesignator().obj.getName(), factorSignle_method);
			factorSignle_method.struct = Tab.noType;
		} else {
			factorSignle_method.struct = factorSignle_method.getDesignator().obj.getType();
			List<Struct> fpList= new ArrayList<>();
			for(Obj local : factorSignle_method.getDesignator().obj.getLocalSymbols()) {
				if((local.getKind() == Obj.Var && local.getLevel() == 1 && local.getFpPos() == 1))
					fpList.add(local.getType());
			}
			ActParsCnt apc = new ActParsCnt();
			factorSignle_method.getActPars().traverseBottomUp(apc);
			List<Struct> apList = apc.finalActParList;
			try {
				if(fpList.size() != apList.size())
					throw new Exception("Greska velicina");
				for(int i = 0; i < fpList.size(); i++) {
					Struct fpStruct = fpList.get(i);
					Struct apStruct = apList.get(i);
					if(!apStruct.assignableTo(fpStruct))
						throw new Exception("Greska tipovi");
				}
				report_info("Pristup globalnoj funkciji: " + factorSignle_method.getDesignator().obj.getName() +
						", Obj(Kind): " + factorSignle_method.getDesignator().obj.getKind() +		
						", Obj(Level): " + factorSignle_method.getDesignator().obj.getLevel() +		
						", Obj(Addr): " + factorSignle_method.getDesignator().obj.getAdr() , factorSignle_method);
			} catch (Exception e) {
				report_error("[" + e.getMessage() + "]" + "Losi parametri pri pozivu metode: " + factorSignle_method.getDesignator().obj.getName(), factorSignle_method);
			}
		}
	}
	
	/*Factor*/
	@Override
	public void visit(Factor factor) {
		if(factor.getUnary() instanceof Unary_minus) {
			if(factor.getFactorSingle().struct.equals(Tab.intType)) {
				factor.struct = Tab.intType;
			} else {
				report_error("Negacija ne int vrednosti!", factor);
				factor.struct = Tab.noType;
			}
		}
		else
			factor.struct = factor.getFactorSingle().struct;
	}
	
	/*Designator*/
	@Override
	public void visit(DesignatorKind_arr designatorKind_arr) {
		if(!designatorKind_arr.getExpr().struct.equals(Tab.intType)) {
			report_error("Pogresno indeksiranje niza[DesignatorKind_arr]!", designatorKind_arr);
			designatorKind_arr.obj = Tab.noObj;
		}
	}
	
	@Override
	public void visit(DesignatorName designatorName) {
		designatorName.obj = Tab.find(designatorName.getI1());
	}
	
	@Override
	public void visit(DesignatorVar designatorVar) {
		
		designatorVar.obj = Tab.find(designatorVar.getI1());
		
		if(designatorVar.obj == Tab.noObj) {
			report_error("Pristup nedefinisanoj promenljivoj/metodi![DesignatorVar]: " + designatorVar.obj.getName(), designatorVar);
			designatorVar.obj = Tab.noObj;
		}
		else if(designatorVar.obj.getKind() != Obj.Con && designatorVar.obj.getKind() != Obj.Var && designatorVar.obj.getKind() != boolType.getKind() && designatorVar.obj.getKind() != Obj.Meth) {
			report_error("Pristup neadekvatnoj promenljivoj![DesignatorVar]: " + designatorVar.obj.getName(), designatorVar);
			designatorVar.obj = Tab.noObj;
		} else {
			if(designatorVar.obj.getKind() == Obj.Meth)
				designatorVar.obj = Tab.find(designatorVar.obj.getName());
			else {
				//designatorVar.obj = designatorObj; //new Obj(designatorObj.getKind(), designatorObj.getName(), designatorObj.getType());
				if(designatorVar.obj.getKind() == Obj.Con)
					report_info("Pristup simbolickoj konstanti: " + designatorVar.obj.getName() +
							", Obj(Kind): " + designatorVar.obj.getKind() +		
							", Obj(Level): " + designatorVar.obj.getLevel() +		
							", Obj(Addr): " + designatorVar.obj.getAdr() +
							", Obj(type): "+ designatorVar.obj.getType().getKind(), designatorVar);
				else {
					if(designatorVar.obj.getLevel() == 0)
						report_info("Pristup globalnoj promenljivi: " + designatorVar.obj.getName() +
								", Obj(Kind): " + designatorVar.obj.getKind() +		
								", Obj(Level): " + designatorVar.obj.getLevel() +		
								", Obj(Addr): " + designatorVar.obj.getAdr()+
								", Obj(type): "+ designatorVar.obj.getType().getKind() , designatorVar);
					else {
						if(designatorVar.obj.getFpPos() == 0)
							report_info("Pristup lokalnoj promenljivi: " + designatorVar.obj.getName() +
									", Obj(Kind): " + designatorVar.obj.getKind() +		
									", Obj(Level): " + designatorVar.obj.getLevel() +		
									", Obj(Addr): " + designatorVar.obj.getAdr() +
									", Obj(type): "+ designatorVar.obj.getType().getKind(), designatorVar);
						else
							report_info("Pristup formalnom argumentu f-je: " + designatorVar.obj.getName() +
									", Obj(Kind): " + designatorVar.obj.getKind() +		
									", Obj(Level): " + designatorVar.obj.getLevel() +		
									", Obj(Addr): " + designatorVar.obj.getAdr()+
									", Obj(type): "+ designatorVar.obj.getType().getKind(), designatorVar);
					}
				}
			}
		}
	}
	
	@Override
	public void visit(DesignatorArrEnum designatorArrEnum) {
		Obj designatorObj = designatorArrEnum.getDesignatorName().obj;
		if(designatorArrEnum.getDesignatorKind() instanceof DesignatorKind_arr) { //designator je neki elemnent niza
			
			if(designatorObj == Tab.noObj || designatorObj.getType().getKind() != Struct.Array) {
				report_error("Pristup nedefinisanom nizu![Designator]: " + designatorObj.getName(), designatorArrEnum);
				designatorArrEnum.obj = Tab.noObj;
			}
			else if(designatorObj.getKind() != Obj.Var){
				report_error("Neadekvatna promenljiva niza![Designator]: " + designatorObj.getName(), designatorArrEnum);
				designatorArrEnum.obj = Tab.noObj;
			}
			else if(designatorArrEnum.getDesignatorKind().obj == Tab.noObj)
				designatorArrEnum.obj = Tab.noObj;
			else {
				designatorArrEnum.obj = new Obj(Obj.Elem, designatorObj.getName(), designatorObj.getType().getElemType());
				designatorArrEnum.obj.setAdr(designatorObj.getAdr());
				designatorArrEnum.obj.setLevel(designatorObj.getLevel());
				report_info("Pristup elementu niza: " + designatorArrEnum.obj.getName() +
					", Obj(Kind): " + designatorArrEnum.obj.getKind() +		//za kind se ispisuje tip element
					", Obj(Level): " + designatorArrEnum.obj.getLevel() +
					", Obj(Addr): " + designatorArrEnum.obj.getAdr() +
					", Obj(Type): " + designatorArrEnum.obj.getType().getKind() , designatorArrEnum);
			}
		} else if (designatorArrEnum.getDesignatorKind() instanceof DesignatorKind_len) { //trazila se duzina niza
			
			if(designatorObj == Tab.noObj || designatorObj.getType().getKind() != Struct.Array) {
				report_error("Pristup nedefinisanom nizu![Designator]: " + designatorObj.getName(), designatorArrEnum);
				designatorArrEnum.obj = Tab.noObj;
			}
			else
				designatorArrEnum.obj = new Obj(Obj.Con, "LENGTH", Tab.intType);
				designatorArrEnum.obj.setAdr(designatorArrEnum.getDesignatorName().obj.getAdr());
		} else if(designatorArrEnum.getDesignatorKind() instanceof DesignatorKind_enum) { //nabrajanje u enumu
			
			if(designatorObj == Tab.noObj || designatorObj.getType().getKind() != Struct.Enum) {
				report_error("Pristup nedefinisanom enumu![Designator]: " + designatorObj.getName(), designatorArrEnum);
				designatorArrEnum.obj = Tab.noObj;
			} else {
				DesignatorKind_enum enumField = (DesignatorKind_enum) designatorArrEnum.getDesignatorKind();
				String fieldName = enumField.getI1();
				//System.out.println("EnumFieldName: " + fieldName);
				for (Obj field : designatorObj.getType().getMembers()) {
					if(fieldName.equals(field.getName())) {
						//System.out.println("EnumFieldName: " + field.getName() + ", vrednost: " + field.getAdr() + ", kind: " + field.getKind());
						designatorArrEnum.obj = field;//designatorArrEnum.getDesignatorKind().obj;//new Obj(Obj.Con, fieldName, Tab.intType);
						return;
					}
				}
				report_error("Ne postoji takvo polje u datom enumu![Designator]: " + designatorObj.getName(), designatorArrEnum);
				designatorArrEnum.obj = Tab.noObj;
			}
		}
	}
	
	/*DesignatorStatement*/
	@Override
	public void visit(DesignatorStatement_assign designatorStatement_assign) {
		if (designatorStatement_assign.getDesignator().obj.getKind() == Obj.Elem ||
				designatorStatement_assign.getDesignator().obj.getKind() == Obj.Var) {
			
			if(designatorStatement_assign.getExpr().struct.getKind() == Struct.Enum && designatorStatement_assign.getDesignator().obj.getType().equals(Tab.intType))
				designatorStatement_assign.getExpr().struct = Tab.intType;
			
			if(!designatorStatement_assign.getExpr().struct.assignableTo(designatorStatement_assign.getDesignator().obj.getType())) {
				report_error("Losa dodela vrednosti![DesignatorStatement_assign]: " + designatorStatement_assign.getDesignator().obj.getName() +
						", Tip: " + designatorStatement_assign.getDesignator().obj.getType().getKind(), designatorStatement_assign);
			}
		} else
			report_error("Losa leva strana dodele vrednosti![DesignatorStatement_assign]!", designatorStatement_assign);
	}
	
	@Override
	public void visit(DesignatorStatement_inc designatorStatement_inc) {
		int kind = designatorStatement_inc.getDesignator().obj.getKind();
		if(kind != Obj.Var && kind != Obj.Elem) 
			report_error("Inkrementiranje neadekvatne promenljive![DesignatorStatement_inc]! " + designatorStatement_inc.getDesignator().obj.getName(), designatorStatement_inc);
		else if(!designatorStatement_inc.getDesignator().obj.getType().equals(Tab.intType))
			report_error("Inkrementiranje ne int promenljive![DesignatorStatement_inc]!" + designatorStatement_inc.getDesignator().obj.getName(), designatorStatement_inc);
	}
	
	@Override
	public void visit(DesignatorStatement_dec designatorStatement_dec) {
		int kind = designatorStatement_dec.getDesignator().obj.getKind();
		if(kind != Obj.Var && kind != Obj.Elem) 
			report_error("Dekrementiranje neadekvatne promenljive![DesignatorStatement_dec]! " + designatorStatement_dec.getDesignator().obj.getName(), designatorStatement_dec);
		else if(!designatorStatement_dec.getDesignator().obj.getType().equals(Tab.intType))
			report_error("Dekrementiranje ne int promenljive![DesignatorStatement_dec]! " + designatorStatement_dec.getDesignator().obj.getName(), designatorStatement_dec);
	}
	
	@Override
	public void visit(DesignatorStatement_meth designatorStatement_meth) {
		if(designatorStatement_meth.getDesignator().obj.getKind() != Obj.Meth || designatorStatement_meth.getDesignator().obj.getName().equals("main"))
			report_error("Poziv neadekvatne metode[DesignatorStatement_meth]!", designatorStatement_meth);
		else {
			List<Struct> fpList= new ArrayList<>();
			for(Obj local : designatorStatement_meth.getDesignator().obj.getLocalSymbols()) {
				if((local.getKind() == Obj.Var && local.getLevel() == 1 && local.getFpPos() == 1) || (designatorStatement_meth.getDesignator().obj.getName().equals("chr")) ||
						(designatorStatement_meth.getDesignator().obj.getName().equals("ord")) || (designatorStatement_meth.getDesignator().obj.getName().equals("len")))
					fpList.add(local.getType());
			}
			ActParsCnt apc = new ActParsCnt();
			designatorStatement_meth.getActPars().traverseBottomUp(apc);
			List<Struct> apList = apc.finalActParList;
			
			try {
				if(fpList.size() != apList.size())
					throw new Exception("Greska velicina");
				for(int i = 0; i < fpList.size(); i++) {
					Struct fpStruct = fpList.get(i);
					Struct apStruct = apList.get(i);
					if(!apStruct.assignableTo(fpStruct))
						throw new Exception("Greska tipovi");
				}
				report_info("Pristup globalnoj funkciji: " + designatorStatement_meth.getDesignator().obj.getName() +
						", Obj(Kind): " + designatorStatement_meth.getDesignator().obj.getKind() +		
						", Obj(Level): " + designatorStatement_meth.getDesignator().obj.getLevel() +		
						", Obj(Addr): " + designatorStatement_meth.getDesignator().obj.getAdr() , designatorStatement_meth);
			} catch (Exception e) {
				report_error("[" + e.getMessage() + "]" + "Los broj parametara pri pozivu metode: " + designatorStatement_meth.getDesignator().obj.getName(), designatorStatement_meth);
			}
		}
	} 
	
	/*Term */
	@Override
	public void visit(Term_factor term_factor) {
		term_factor.struct = term_factor.getFactor().struct;
	}
	
	@Override
	public void visit(Term_mulfactor term_mulfactor) {
		Struct left = term_mulfactor.getTerm().struct;
		Struct right = term_mulfactor.getFactor().struct;
		
		if((left.equals(Tab.intType) && right.equals(Tab.intType)) || (left.getKind() == Struct.Enum && right.equals(Tab.intType)) || (left.equals(Tab.intType) && right.getKind() == Struct.Enum) || 
				(left.getKind() == Struct.Enum && right.getKind() == Struct.Enum))
			term_mulfactor.struct = Tab.intType;
		else {
			report_error("Mnozenje ne int vrednosti![Term_mulfactor]!", term_mulfactor);
			term_mulfactor.struct = Tab.noType;
		}
	}
	
	/*AddopTerm*/
	@Override
	public void visit(AddopTermList_list addopTermList_list) {
		Struct left = addopTermList_list.getAddopTermList().struct;
		Struct right = addopTermList_list.getTerm().struct;
		if((left.equals(Tab.intType) && right.equals(Tab.intType)) || (left.getKind() == Struct.Enum && right.equals(Tab.intType)) || (left.equals(Tab.intType) && right.getKind() ==Struct.Enum) ||
				(left.getKind() == Struct.Enum && right.getKind() == Struct.Enum))
			addopTermList_list.struct = Tab.intType;
		else {
			report_error("Sabiranje ne int vrednosti![AddopTermList_list]!", addopTermList_list);
			addopTermList_list.struct = Tab.noType;
		}
	}
	
	@Override
	public void visit(AddopTermList_term addopTermList_term) {
		addopTermList_term.struct = addopTermList_term.getTerm().struct;
	}
	
	/*Expr*/
	@Override
	public void visit(Expr_term expr_term) {
		expr_term.struct = expr_term.getAddopTermList().struct;
	}
	
	/*Statement*/
	@Override
	public void visit(Statement_read statement_read) {
		int kind = statement_read.getDesignator().obj.getKind();
		Struct type = statement_read.getDesignator().obj.getType();
		if(kind != Obj.Var && kind != Obj.Elem) 
			report_error("Citanje neadekvatne promenljive![Statement_read]! " + statement_read.getDesignator().obj.getName(), statement_read);
		else if(!type.equals(Tab.intType) && !type.equals(Tab.charType) && !type.equals(boolType))
			report_error("Citanje ne int/bool/char promenljive![Statement_read]!" + statement_read.getDesignator().obj.getName(), statement_read);
	}
	
	@Override
	public void visit(Statement_print1 statement_print1) {
		Struct type = statement_print1.getExpr().struct;
		if(!type.equals(Tab.intType) && !type.equals(Tab.charType) && !type.equals(boolType))
			report_error("Print ne int/bool/char izraza![Statement_print1]!", statement_print1);
	}
	
	@Override
	public void visit(Statement_print2 statement_print2) {
		Struct type = statement_print2.getExpr().struct;
		if(!type.equals(Tab.intType) && !type.equals(Tab.charType) && !type.equals(boolType))
			report_error("Print ne int/bool/char izraza![Statement_print2]!", statement_print2);
	}
	
	@Override
	public void visit(Statement_return1 statement_return1) {
		if(currentMethod == null) {
			report_error("Return izvan tela metode![Statement_return1]!", statement_return1);
			returnHappend = false;
		}
		else if(currentMethod.getType().getKind() != Struct.None) {
			report_error("Pozivanje return-a bez expresiona u ne void metodi![Statement_return1]!", statement_return1);
			returnHappend = false;
		}
		else
			returnHappend = true;
	}
	
	@Override
	public void visit(Statement_return2 statement_return2) {
		if(currentMethod == null) { 
			report_error("Return izvan tela metode![Statement_return2]!", statement_return2);
			returnHappend = false;
		}
		else if(statement_return2.getExpr().struct == Tab.noType || statement_return2.getExpr().struct.getKind() == Struct.Array ||
				currentMethod.getType().getKind() != statement_return2.getExpr().struct.getKind()) {	//ovaj poslednji uslov znaci da je u pitanju ime metode
			
			report_error("Return sa pogresnim parametrom![Statement_return2]!", statement_return2);
			returnHappend = false;
		}
		else
			returnHappend = true;
	}
	
	@Override
	public void visit(SwitchExpr switchExpr) {
		Struct exprType = switchExpr.getExpr().struct;
		if(!exprType.equals(Tab.intType)) {
			report_error("Argument switch-a mora biti int!", switchExpr);
		}
		
	}
	
	@Override
	public void visit(Statement_switch statementSwitch) {
		switchCnt--;
		caseNums.pop();
	}
	
	@Override
	public void visit(Statement_for statement_for) {
		forCnt--;
	}
	
	@Override
	public void visit(Statement_br statement_br) {
		if(switchCnt + forCnt == 0)
			report_error("Break naredba nije unutar switch/for bloka!", statement_br);
	}
	
	@Override
	public void visit(Statement_continue statement_continue) {
		if(forCnt == 0)
			report_error("Continue naredba nije unutar for bloka!", statement_continue);
	}
	
	/*NonTerminalSwitch*/
	@Override
	public void visit(NonTerminalSwitch nonTerminalSwitch) {
		caseNums.push(new ArrayList<Integer>());
		switchCnt++;
	}
	
	@Override
	public void visit(CaseNum caseNum) {
		if(caseNums.peek().indexOf(caseNum.getConstantNUM().getN1()) != -1)
			report_error("Dvostruka vrednost unutar switcha!", caseNum);
		else
			caseNums.peek().add(caseNum.getConstantNUM().getN1());
	}
	
	/*NonTerminalFor*/
	@Override
	public void visit(NonTerminalFor nonTerminalFor) {
		forCnt++;
	}
	
	/*CondFact*/
	@Override
	public void visit(CondFact_expr condFact_expr) {
		if(!condFact_expr.getAddopTermList().struct.equals(boolType)) {
			report_error("Operand u conditionu nije bool![CondFact_expr]!", condFact_expr);
			condFact_expr.struct = Tab.noType;
		} else
			condFact_expr.struct = boolType;
	}
	
	@Override
	public void visit(CondFact_rel condFact_rel) {
		Struct left = condFact_rel.getAddopTermList().struct;
		Struct right = condFact_rel.getAddopTermList1().struct;
		
		if(left.compatibleWith(right)) {
			if(left.isRefType() || right.isRefType()) {
				if(condFact_rel.getRelop() instanceof RelOP_eq || condFact_rel.getRelop() instanceof RelOP_noeq)
					condFact_rel.struct = boolType;
				else {
					report_error("Poredjenje ref tipova losim operatorom![CondFact_rel]!", condFact_rel);
					condFact_rel.struct = Tab.noType;
				}
			}
			else 
				condFact_rel.struct = boolType;
		}
		else {
			report_error("Uporedjivanje losih vrednosti![CondFact_rel]!", condFact_rel);
			condFact_rel.struct = Tab.noType;
		}
	}
	
	/*CondFactList*/
	@Override
	public void visit(CondFactList_list condFactList_list) {
		Struct left = condFactList_list.getCondFactList().struct;
		Struct right = condFactList_list.getCondFact().struct;
		
		if(left.equals(boolType) && right.equals(boolType))
			condFactList_list.struct = boolType;
		else {
			report_error("And nad ne bool vrednostima![CondFactList_list]!", condFactList_list);
			condFactList_list.struct = Tab.noType;
		}
	}
	
	@Override
	public void visit(CondFactList_fact condFactList_fact) {
		condFactList_fact.struct = condFactList_fact.getCondFact().struct;
	}
	
	/*CondTerm*/
	@Override
	public void visit(CondTerm condTerm) {
		condTerm.struct = condTerm.getCondFactList().struct;
	}
	
	/*CondTermList*/
	@Override
	public void visit(CondTermList_term condTermList_term) {
		condTermList_term.struct = condTermList_term.getCondTerm().struct;
	}
	
	@Override
	public void visit(CondTermList_list condTermList_list) {
		Struct left = condTermList_list.getCondTermList().struct;
		Struct right = condTermList_list.getCondTerm().struct;
		
		if(left.equals(boolType) && right.equals(boolType))
			condTermList_list.struct = boolType;
		else {
			report_error("Or nad ne bool vrednostima![CondTermList_list]!", condTermList_list);
			condTermList_list.struct = Tab.noType;
		}
	}
	
	/*Condition*/
	@Override
	public void visit(Condition condition) {
		condition.struct = condition.getCondTermList().struct;
		if(!condition.struct.equals(boolType)) 
			report_error("Uslov u conditionu nije tipa bool![Condition]!", condition);
	}
	
	/*MayCond*/
	@Override
	public void visit(MayCond_yes mayCond_yes) {
		mayCond_yes.struct = mayCond_yes.getCondition().struct;
	}
	
	@Override
	public void visit(MayCond_eps mayCond_eps) {
		mayCond_eps.struct = Tab.noType;
	}
	
	/*For args*/
	@Override
	public void visit(For_args for_args) {
		Struct condType = for_args.getMayCond().struct;
		if(!condType.equals(boolType) && condType != Tab.noType) 
			report_error("Uslov u for conditionu nije tipa bool![For_args]!", for_args);
	}
	
	/*Expr_cond*/
	@Override
	public void visit(Expr_cond expr_cond) {
		Struct left = expr_cond.getTernaniTrue().struct;
		Struct right = expr_cond.getTernaniFalse().struct;
		
		if(!left.equals(right)) {
			report_error("Izrazi u ternanom operaotru nisu istog tipa![Expr_cond]!", expr_cond);
			expr_cond.struct = Tab.noType;
		} else
			expr_cond.struct = left;		
	}
	
	/*Ternani*/
	@Override
	public void visit(TernaniTrue ternaniTrue) {
		ternaniTrue.struct = ternaniTrue.getExpr().struct;
	}
	
	@Override
	public void visit(TernaniFalse ternaniFalse) {
		ternaniFalse.struct = ternaniFalse.getExpr().struct;
	}
}