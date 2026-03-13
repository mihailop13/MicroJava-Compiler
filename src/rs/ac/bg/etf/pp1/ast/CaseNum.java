// generated with ast extension for cup
// version 0.8
// 6/2/2026 22:45:30


package rs.ac.bg.etf.pp1.ast;

public class CaseNum implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private ConstantNUM ConstantNUM;

    public CaseNum (ConstantNUM ConstantNUM) {
        this.ConstantNUM=ConstantNUM;
        if(ConstantNUM!=null) ConstantNUM.setParent(this);
    }

    public ConstantNUM getConstantNUM() {
        return ConstantNUM;
    }

    public void setConstantNUM(ConstantNUM ConstantNUM) {
        this.ConstantNUM=ConstantNUM;
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
        buffer.append("CaseNum(\n");

        if(ConstantNUM!=null)
            buffer.append(ConstantNUM.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CaseNum]");
        return buffer.toString();
    }
}
