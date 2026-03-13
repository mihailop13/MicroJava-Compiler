package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.*;
import rs.ac.bg.etf.pp1.ast.*;

public class ActParsCnt extends VisitorAdaptor {

	List<Struct> finalActParList;
	
	Stack<List<Struct>> stackParLists = new Stack<>();
	
	@Override
	public void visit(ActParsBegin actParsBegin) {
		stackParLists.push(new ArrayList<>());
	}
	
	@Override
	public void visit(ActPar actPar) {
		stackParLists.peek().add(actPar.getExpr().struct);
	}
	
	@Override
	public void visit(ActPars_params actPars_params) {
		finalActParList = stackParLists.pop();
	}
	
	@Override
	public void visit(ActPars_epsilon ActPars_epsilon) {
		finalActParList = stackParLists.pop();
	}
}