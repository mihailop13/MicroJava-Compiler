// generated with ast extension for cup
// version 0.8
// 6/2/2026 22:45:30


package rs.ac.bg.etf.pp1.ast;

public class Constant_CHAR extends Constant {

    private ConstantCHAR ConstantCHAR;

    public Constant_CHAR (ConstantCHAR ConstantCHAR) {
        this.ConstantCHAR=ConstantCHAR;
        if(ConstantCHAR!=null) ConstantCHAR.setParent(this);
    }

    public ConstantCHAR getConstantCHAR() {
        return ConstantCHAR;
    }

    public void setConstantCHAR(ConstantCHAR ConstantCHAR) {
        this.ConstantCHAR=ConstantCHAR;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstantCHAR!=null) ConstantCHAR.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstantCHAR!=null) ConstantCHAR.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstantCHAR!=null) ConstantCHAR.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Constant_CHAR(\n");

        if(ConstantCHAR!=null)
            buffer.append(ConstantCHAR.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Constant_CHAR]");
        return buffer.toString();
    }
}
