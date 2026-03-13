// generated with ast extension for cup
// version 0.8
// 6/2/2026 22:45:30


package rs.ac.bg.etf.pp1.ast;

public class ConstVarEnumDecl_const extends ConstVarEnumDecl {

    private ConstVarEnumDecl ConstVarEnumDecl;
    private ConstDecl ConstDecl;

    public ConstVarEnumDecl_const (ConstVarEnumDecl ConstVarEnumDecl, ConstDecl ConstDecl) {
        this.ConstVarEnumDecl=ConstVarEnumDecl;
        if(ConstVarEnumDecl!=null) ConstVarEnumDecl.setParent(this);
        this.ConstDecl=ConstDecl;
        if(ConstDecl!=null) ConstDecl.setParent(this);
    }

    public ConstVarEnumDecl getConstVarEnumDecl() {
        return ConstVarEnumDecl;
    }

    public void setConstVarEnumDecl(ConstVarEnumDecl ConstVarEnumDecl) {
        this.ConstVarEnumDecl=ConstVarEnumDecl;
    }

    public ConstDecl getConstDecl() {
        return ConstDecl;
    }

    public void setConstDecl(ConstDecl ConstDecl) {
        this.ConstDecl=ConstDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstVarEnumDecl!=null) ConstVarEnumDecl.accept(visitor);
        if(ConstDecl!=null) ConstDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstVarEnumDecl!=null) ConstVarEnumDecl.traverseTopDown(visitor);
        if(ConstDecl!=null) ConstDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstVarEnumDecl!=null) ConstVarEnumDecl.traverseBottomUp(visitor);
        if(ConstDecl!=null) ConstDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstVarEnumDecl_const(\n");

        if(ConstVarEnumDecl!=null)
            buffer.append(ConstVarEnumDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstDecl!=null)
            buffer.append(ConstDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstVarEnumDecl_const]");
        return buffer.toString();
    }
}
