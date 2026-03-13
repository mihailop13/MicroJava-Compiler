// generated with ast extension for cup
// version 0.8
// 6/2/2026 22:45:30


package rs.ac.bg.etf.pp1.ast;

public class ActPars_params extends ActPars {

    private ActParsBegin ActParsBegin;
    private ActPar ActPar;
    private ActParsNext ActParsNext;

    public ActPars_params (ActParsBegin ActParsBegin, ActPar ActPar, ActParsNext ActParsNext) {
        this.ActParsBegin=ActParsBegin;
        if(ActParsBegin!=null) ActParsBegin.setParent(this);
        this.ActPar=ActPar;
        if(ActPar!=null) ActPar.setParent(this);
        this.ActParsNext=ActParsNext;
        if(ActParsNext!=null) ActParsNext.setParent(this);
    }

    public ActParsBegin getActParsBegin() {
        return ActParsBegin;
    }

    public void setActParsBegin(ActParsBegin ActParsBegin) {
        this.ActParsBegin=ActParsBegin;
    }

    public ActPar getActPar() {
        return ActPar;
    }

    public void setActPar(ActPar ActPar) {
        this.ActPar=ActPar;
    }

    public ActParsNext getActParsNext() {
        return ActParsNext;
    }

    public void setActParsNext(ActParsNext ActParsNext) {
        this.ActParsNext=ActParsNext;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ActParsBegin!=null) ActParsBegin.accept(visitor);
        if(ActPar!=null) ActPar.accept(visitor);
        if(ActParsNext!=null) ActParsNext.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ActParsBegin!=null) ActParsBegin.traverseTopDown(visitor);
        if(ActPar!=null) ActPar.traverseTopDown(visitor);
        if(ActParsNext!=null) ActParsNext.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ActParsBegin!=null) ActParsBegin.traverseBottomUp(visitor);
        if(ActPar!=null) ActPar.traverseBottomUp(visitor);
        if(ActParsNext!=null) ActParsNext.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ActPars_params(\n");

        if(ActParsBegin!=null)
            buffer.append(ActParsBegin.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ActPar!=null)
            buffer.append(ActPar.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ActParsNext!=null)
            buffer.append(ActParsNext.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ActPars_params]");
        return buffer.toString();
    }
}
