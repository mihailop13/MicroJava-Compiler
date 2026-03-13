// generated with ast extension for cup
// version 0.8
// 6/2/2026 22:45:30


package rs.ac.bg.etf.pp1.ast;

public class Constant_NUM extends Constant {

    private ConstantNUM ConstantNUM;

    public Constant_NUM (ConstantNUM ConstantNUM) {
        this.ConstantNUM=ConstantNUM;
        if(ConstantNUM!=null) ConstantNUM.setParent(this);
    }

    public ConstantNUM getConstantNUM() {
        return ConstantNUM;
    }

    public void setConstantNUM(ConstantNUM ConstantNUM) {
        this.ConstantNUM=ConstantNUM;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstantNUM!=null) ConstantNUM.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstantNUM!=null) ConstantNUM.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstantNUM!=null) ConstantNUM.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Constant_NUM(\n");

        if(ConstantNUM!=null)
            buffer.append(ConstantNUM.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Constant_NUM]");
        return buffer.toString();
    }
}
