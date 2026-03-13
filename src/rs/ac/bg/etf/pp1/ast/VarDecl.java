// generated with ast extension for cup
// version 0.8
// 6/2/2026 22:45:30


package rs.ac.bg.etf.pp1.ast;

public class VarDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private Type Type;
    private VarArray VarArray;
    private VarArrayNext VarArrayNext;

    public VarDecl (Type Type, VarArray VarArray, VarArrayNext VarArrayNext) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.VarArray=VarArray;
        if(VarArray!=null) VarArray.setParent(this);
        this.VarArrayNext=VarArrayNext;
        if(VarArrayNext!=null) VarArrayNext.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public VarArray getVarArray() {
        return VarArray;
    }

    public void setVarArray(VarArray VarArray) {
        this.VarArray=VarArray;
    }

    public VarArrayNext getVarArrayNext() {
        return VarArrayNext;
    }

    public void setVarArrayNext(VarArrayNext VarArrayNext) {
        this.VarArrayNext=VarArrayNext;
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
        if(VarArray!=null) VarArray.accept(visitor);
        if(VarArrayNext!=null) VarArrayNext.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(VarArray!=null) VarArray.traverseTopDown(visitor);
        if(VarArrayNext!=null) VarArrayNext.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(VarArray!=null) VarArray.traverseBottomUp(visitor);
        if(VarArrayNext!=null) VarArrayNext.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDecl(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarArray!=null)
            buffer.append(VarArray.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarArrayNext!=null)
            buffer.append(VarArrayNext.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDecl]");
        return buffer.toString();
    }
}
