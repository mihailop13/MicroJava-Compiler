// generated with ast extension for cup
// version 0.8
// 6/2/2026 22:45:30


package rs.ac.bg.etf.pp1.ast;

public class ConstDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private Type Type;
    private ConstDeclFinal ConstDeclFinal;
    private ConstDeclFinalNext ConstDeclFinalNext;

    public ConstDecl (Type Type, ConstDeclFinal ConstDeclFinal, ConstDeclFinalNext ConstDeclFinalNext) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.ConstDeclFinal=ConstDeclFinal;
        if(ConstDeclFinal!=null) ConstDeclFinal.setParent(this);
        this.ConstDeclFinalNext=ConstDeclFinalNext;
        if(ConstDeclFinalNext!=null) ConstDeclFinalNext.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public ConstDeclFinal getConstDeclFinal() {
        return ConstDeclFinal;
    }

    public void setConstDeclFinal(ConstDeclFinal ConstDeclFinal) {
        this.ConstDeclFinal=ConstDeclFinal;
    }

    public ConstDeclFinalNext getConstDeclFinalNext() {
        return ConstDeclFinalNext;
    }

    public void setConstDeclFinalNext(ConstDeclFinalNext ConstDeclFinalNext) {
        this.ConstDeclFinalNext=ConstDeclFinalNext;
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
        if(Type!=null) Type.accept(visitor);
        if(ConstDeclFinal!=null) ConstDeclFinal.accept(visitor);
        if(ConstDeclFinalNext!=null) ConstDeclFinalNext.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(ConstDeclFinal!=null) ConstDeclFinal.traverseTopDown(visitor);
        if(ConstDeclFinalNext!=null) ConstDeclFinalNext.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(ConstDeclFinal!=null) ConstDeclFinal.traverseBottomUp(visitor);
        if(ConstDeclFinalNext!=null) ConstDeclFinalNext.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDecl(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstDeclFinal!=null)
            buffer.append(ConstDeclFinal.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstDeclFinalNext!=null)
            buffer.append(ConstDeclFinalNext.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDecl]");
        return buffer.toString();
    }
}
