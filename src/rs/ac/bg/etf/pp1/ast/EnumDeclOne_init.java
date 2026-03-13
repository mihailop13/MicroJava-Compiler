// generated with ast extension for cup
// version 0.8
// 6/2/2026 22:45:30


package rs.ac.bg.etf.pp1.ast;

public class EnumDeclOne_init extends EnumDeclOne {

    private String I1;
    private ConstantNUM ConstantNUM;

    public EnumDeclOne_init (String I1, ConstantNUM ConstantNUM) {
        this.I1=I1;
        this.ConstantNUM=ConstantNUM;
        if(ConstantNUM!=null) ConstantNUM.setParent(this);
    }

    public String getI1() {
        return I1;
    }

    public void setI1(String I1) {
        this.I1=I1;
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
        buffer.append("EnumDeclOne_init(\n");

        buffer.append(" "+tab+I1);
        buffer.append("\n");

        if(ConstantNUM!=null)
            buffer.append(ConstantNUM.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [EnumDeclOne_init]");
        return buffer.toString();
    }
}
