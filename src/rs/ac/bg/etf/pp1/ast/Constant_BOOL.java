// generated with ast extension for cup
// version 0.8
// 6/2/2026 22:45:30


package rs.ac.bg.etf.pp1.ast;

public class Constant_BOOL extends Constant {

    private ConstantBOOL ConstantBOOL;

    public Constant_BOOL (ConstantBOOL ConstantBOOL) {
        this.ConstantBOOL=ConstantBOOL;
        if(ConstantBOOL!=null) ConstantBOOL.setParent(this);
    }

    public ConstantBOOL getConstantBOOL() {
        return ConstantBOOL;
    }

    public void setConstantBOOL(ConstantBOOL ConstantBOOL) {
        this.ConstantBOOL=ConstantBOOL;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstantBOOL!=null) ConstantBOOL.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstantBOOL!=null) ConstantBOOL.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstantBOOL!=null) ConstantBOOL.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Constant_BOOL(\n");

        if(ConstantBOOL!=null)
            buffer.append(ConstantBOOL.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Constant_BOOL]");
        return buffer.toString();
    }
}
