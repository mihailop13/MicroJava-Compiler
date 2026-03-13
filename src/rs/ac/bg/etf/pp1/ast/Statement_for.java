// generated with ast extension for cup
// version 0.8
// 6/2/2026 22:45:30


package rs.ac.bg.etf.pp1.ast;

public class Statement_for extends Statement {

    private NonTerminalFor NonTerminalFor;
    private For_args For_args;
    private ForStatement ForStatement;

    public Statement_for (NonTerminalFor NonTerminalFor, For_args For_args, ForStatement ForStatement) {
        this.NonTerminalFor=NonTerminalFor;
        if(NonTerminalFor!=null) NonTerminalFor.setParent(this);
        this.For_args=For_args;
        if(For_args!=null) For_args.setParent(this);
        this.ForStatement=ForStatement;
        if(ForStatement!=null) ForStatement.setParent(this);
    }

    public NonTerminalFor getNonTerminalFor() {
        return NonTerminalFor;
    }

    public void setNonTerminalFor(NonTerminalFor NonTerminalFor) {
        this.NonTerminalFor=NonTerminalFor;
    }

    public For_args getFor_args() {
        return For_args;
    }

    public void setFor_args(For_args For_args) {
        this.For_args=For_args;
    }

    public ForStatement getForStatement() {
        return ForStatement;
    }

    public void setForStatement(ForStatement ForStatement) {
        this.ForStatement=ForStatement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(NonTerminalFor!=null) NonTerminalFor.accept(visitor);
        if(For_args!=null) For_args.accept(visitor);
        if(ForStatement!=null) ForStatement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(NonTerminalFor!=null) NonTerminalFor.traverseTopDown(visitor);
        if(For_args!=null) For_args.traverseTopDown(visitor);
        if(ForStatement!=null) ForStatement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(NonTerminalFor!=null) NonTerminalFor.traverseBottomUp(visitor);
        if(For_args!=null) For_args.traverseBottomUp(visitor);
        if(ForStatement!=null) ForStatement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Statement_for(\n");

        if(NonTerminalFor!=null)
            buffer.append(NonTerminalFor.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(For_args!=null)
            buffer.append(For_args.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForStatement!=null)
            buffer.append(ForStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Statement_for]");
        return buffer.toString();
    }
}
