// generated with ast extension for cup
// version 0.8
// 6/2/2026 22:45:30


package rs.ac.bg.etf.pp1.ast;

public class FormParsVarArrayNext_var extends FormParsVarArrayNext {

    private FormParsVarArray FormParsVarArray;
    private FormParsVarArrayNext FormParsVarArrayNext;

    public FormParsVarArrayNext_var (FormParsVarArray FormParsVarArray, FormParsVarArrayNext FormParsVarArrayNext) {
        this.FormParsVarArray=FormParsVarArray;
        if(FormParsVarArray!=null) FormParsVarArray.setParent(this);
        this.FormParsVarArrayNext=FormParsVarArrayNext;
        if(FormParsVarArrayNext!=null) FormParsVarArrayNext.setParent(this);
    }

    public FormParsVarArray getFormParsVarArray() {
        return FormParsVarArray;
    }

    public void setFormParsVarArray(FormParsVarArray FormParsVarArray) {
        this.FormParsVarArray=FormParsVarArray;
    }

    public FormParsVarArrayNext getFormParsVarArrayNext() {
        return FormParsVarArrayNext;
    }

    public void setFormParsVarArrayNext(FormParsVarArrayNext FormParsVarArrayNext) {
        this.FormParsVarArrayNext=FormParsVarArrayNext;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(FormParsVarArray!=null) FormParsVarArray.accept(visitor);
        if(FormParsVarArrayNext!=null) FormParsVarArrayNext.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FormParsVarArray!=null) FormParsVarArray.traverseTopDown(visitor);
        if(FormParsVarArrayNext!=null) FormParsVarArrayNext.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FormParsVarArray!=null) FormParsVarArray.traverseBottomUp(visitor);
        if(FormParsVarArrayNext!=null) FormParsVarArrayNext.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormParsVarArrayNext_var(\n");

        if(FormParsVarArray!=null)
            buffer.append(FormParsVarArray.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FormParsVarArrayNext!=null)
            buffer.append(FormParsVarArrayNext.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormParsVarArrayNext_var]");
        return buffer.toString();
    }
}
