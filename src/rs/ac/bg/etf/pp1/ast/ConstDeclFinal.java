// generated with ast extension for cup
// version 0.8
// 6/2/2026 22:45:30


package rs.ac.bg.etf.pp1.ast;

public class ConstDeclFinal implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private String I1;
    private AssignOP AssignOP;
    private Constant Constant;

    public ConstDeclFinal (String I1, AssignOP AssignOP, Constant Constant) {
        this.I1=I1;
        this.AssignOP=AssignOP;
        if(AssignOP!=null) AssignOP.setParent(this);
        this.Constant=Constant;
        if(Constant!=null) Constant.setParent(this);
    }

    public String getI1() {
        return I1;
    }

    public void setI1(String I1) {
        this.I1=I1;
    }

    public AssignOP getAssignOP() {
        return AssignOP;
    }

    public void setAssignOP(AssignOP AssignOP) {
        this.AssignOP=AssignOP;
    }

    public Constant getConstant() {
        return Constant;
    }

    public void setConstant(Constant Constant) {
        this.Constant=Constant;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(AssignOP!=null) AssignOP.accept(visitor);
        if(Constant!=null) Constant.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(AssignOP!=null) AssignOP.traverseTopDown(visitor);
        if(Constant!=null) Constant.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(AssignOP!=null) AssignOP.traverseBottomUp(visitor);
        if(Constant!=null) Constant.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDeclFinal(\n");

        buffer.append(" "+tab+I1);
        buffer.append("\n");

        if(AssignOP!=null)
            buffer.append(AssignOP.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Constant!=null)
            buffer.append(Constant.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclFinal]");
        return buffer.toString();
    }
}
