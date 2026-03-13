// generated with ast extension for cup
// version 0.8
// 6/2/2026 22:45:30


package rs.ac.bg.etf.pp1.ast;

public class EnumDeclList_list extends EnumDeclList {

    private EnumDeclOne EnumDeclOne;
    private EnumDeclList EnumDeclList;

    public EnumDeclList_list (EnumDeclOne EnumDeclOne, EnumDeclList EnumDeclList) {
        this.EnumDeclOne=EnumDeclOne;
        if(EnumDeclOne!=null) EnumDeclOne.setParent(this);
        this.EnumDeclList=EnumDeclList;
        if(EnumDeclList!=null) EnumDeclList.setParent(this);
    }

    public EnumDeclOne getEnumDeclOne() {
        return EnumDeclOne;
    }

    public void setEnumDeclOne(EnumDeclOne EnumDeclOne) {
        this.EnumDeclOne=EnumDeclOne;
    }

    public EnumDeclList getEnumDeclList() {
        return EnumDeclList;
    }

    public void setEnumDeclList(EnumDeclList EnumDeclList) {
        this.EnumDeclList=EnumDeclList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(EnumDeclOne!=null) EnumDeclOne.accept(visitor);
        if(EnumDeclList!=null) EnumDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(EnumDeclOne!=null) EnumDeclOne.traverseTopDown(visitor);
        if(EnumDeclList!=null) EnumDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(EnumDeclOne!=null) EnumDeclOne.traverseBottomUp(visitor);
        if(EnumDeclList!=null) EnumDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("EnumDeclList_list(\n");

        if(EnumDeclOne!=null)
            buffer.append(EnumDeclOne.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(EnumDeclList!=null)
            buffer.append(EnumDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [EnumDeclList_list]");
        return buffer.toString();
    }
}
