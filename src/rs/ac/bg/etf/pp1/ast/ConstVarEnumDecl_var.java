// generated with ast extension for cup
// version 0.8
// 6/2/2026 22:45:30


package rs.ac.bg.etf.pp1.ast;

public class ConstVarEnumDecl_var extends ConstVarEnumDecl {

    private ConstVarEnumDecl ConstVarEnumDecl;
    private VarDecl VarDecl;

    public ConstVarEnumDecl_var (ConstVarEnumDecl ConstVarEnumDecl, VarDecl VarDecl) {
        this.ConstVarEnumDecl=ConstVarEnumDecl;
        if(ConstVarEnumDecl!=null) ConstVarEnumDecl.setParent(this);
        this.VarDecl=VarDecl;
        if(VarDecl!=null) VarDecl.setParent(this);
    }

    public ConstVarEnumDecl getConstVarEnumDecl() {
        return ConstVarEnumDecl;
    }

    public void setConstVarEnumDecl(ConstVarEnumDecl ConstVarEnumDecl) {
        this.ConstVarEnumDecl=ConstVarEnumDecl;
    }

    public VarDecl getVarDecl() {
        return VarDecl;
    }

    public void setVarDecl(VarDecl VarDecl) {
        this.VarDecl=VarDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstVarEnumDecl!=null) ConstVarEnumDecl.accept(visitor);
        if(VarDecl!=null) VarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstVarEnumDecl!=null) ConstVarEnumDecl.traverseTopDown(visitor);
        if(VarDecl!=null) VarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstVarEnumDecl!=null) ConstVarEnumDecl.traverseBottomUp(visitor);
        if(VarDecl!=null) VarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstVarEnumDecl_var(\n");

        if(ConstVarEnumDecl!=null)
            buffer.append(ConstVarEnumDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDecl!=null)
            buffer.append(VarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstVarEnumDecl_var]");
        return buffer.toString();
    }
}
