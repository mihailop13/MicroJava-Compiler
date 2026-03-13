// generated with ast extension for cup
// version 0.8
// 6/2/2026 22:45:30


package rs.ac.bg.etf.pp1.ast;

public class Program_error extends Program {

    private ProgramName ProgramName;
    private ConstVarEnumDecl ConstVarEnumDecl;

    public Program_error (ProgramName ProgramName, ConstVarEnumDecl ConstVarEnumDecl) {
        this.ProgramName=ProgramName;
        if(ProgramName!=null) ProgramName.setParent(this);
        this.ConstVarEnumDecl=ConstVarEnumDecl;
        if(ConstVarEnumDecl!=null) ConstVarEnumDecl.setParent(this);
    }

    public ProgramName getProgramName() {
        return ProgramName;
    }

    public void setProgramName(ProgramName ProgramName) {
        this.ProgramName=ProgramName;
    }

    public ConstVarEnumDecl getConstVarEnumDecl() {
        return ConstVarEnumDecl;
    }

    public void setConstVarEnumDecl(ConstVarEnumDecl ConstVarEnumDecl) {
        this.ConstVarEnumDecl=ConstVarEnumDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ProgramName!=null) ProgramName.accept(visitor);
        if(ConstVarEnumDecl!=null) ConstVarEnumDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ProgramName!=null) ProgramName.traverseTopDown(visitor);
        if(ConstVarEnumDecl!=null) ConstVarEnumDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ProgramName!=null) ProgramName.traverseBottomUp(visitor);
        if(ConstVarEnumDecl!=null) ConstVarEnumDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Program_error(\n");

        if(ProgramName!=null)
            buffer.append(ProgramName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstVarEnumDecl!=null)
            buffer.append(ConstVarEnumDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Program_error]");
        return buffer.toString();
    }
}
