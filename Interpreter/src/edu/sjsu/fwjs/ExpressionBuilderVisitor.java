package edu.sjsu.fwjs;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.TerminalNode;

import edu.sjsu.fwjs.parser.FeatherweightJavaScriptBaseVisitor;
import edu.sjsu.fwjs.parser.FeatherweightJavaScriptParser;

public class ExpressionBuilderVisitor extends FeatherweightJavaScriptBaseVisitor<Expression>{

    @Override
    public Expression visitProg(FeatherweightJavaScriptParser.ProgContext ctx) {
        List<Expression> stmts = new ArrayList<Expression>();
        for (int i=0; i<ctx.stat().size(); i++) {
            Expression exp = visit(ctx.stat(i));
            if (exp != null) stmts.add(exp);
        }
        return listToSeqExp(stmts);
    }

    @Override
    public Expression visitBareExpr(FeatherweightJavaScriptParser.BareExprContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Expression visitIfThenElse(FeatherweightJavaScriptParser.IfThenElseContext ctx) {
        Expression cond = visit(ctx.expr());
        Expression thn = visit(ctx.block(0));
        Expression els = visit(ctx.block(1));
        return new IfExpr(cond, thn, els);
    }

    @Override
    public Expression visitIfThen(FeatherweightJavaScriptParser.IfThenContext ctx) {
        Expression cond = visit(ctx.expr());
        Expression thn = visit(ctx.block());
        return new IfExpr(cond, thn, null);
    }

    //while
    @Override
    public Expression visitWhile(FeatherweightJavaScriptParser.WhileContext ctx) {
        Expression cond = visit(ctx.expr());
        Expression body = visit(ctx.block());
        return new WhileExpr(cond, body);        
    }

    //print
    @Override
    public Expression visitPrint(FeatherweightJavaScriptParser.PrintContext ctx) {
        Expression exp = visit(ctx.expr());
        return new PrintExpr(exp);
    }

    //emptyStat
    @Override 
    public Expression visitEmptyStat(FeatherweightJavaScriptParser.EmptyStatContext ctx) {
        return new ValueExpr(new NullVal());
    }

    //MulDivMod
    @Override
    public Expression visitMulDivMod(FeatherweightJavaScriptParser.MulDivModContext ctx) {
        Expression left = visit(ctx.expr(0));
        Expression right = visit(ctx.expr(1));
        if (ctx.op.getType() == FeatherweightJavaScriptParser.MUL) {
            return new BinOpExpr(Op.MULTIPLY, left, right);
        }
        else if (ctx.op.getType() == FeatherweightJavaScriptParser.DIV) {
            return new BinOpExpr(Op.DIVIDE, left, right);
        }
        else {
            return new BinOpExpr(Op.MOD, left, right);
        }
    }

    //AddSub
    @Override
    public Expression visitAddSub(FeatherweightJavaScriptParser.AddSubContext ctx) {
        Expression left = visit(ctx.expr(0));
        Expression right = visit(ctx.expr(1));
        if (ctx.op.getType() == FeatherweightJavaScriptParser.ADD) {
            return new BinOpExpr(Op.ADD, left, right);
        }
        else {
            return new BinOpExpr(Op.SUBTRACT, left, right);
        }
    }

    //Compare
    @Override
    public Expression visitCompare(FeatherweightJavaScriptParser.CompareContext ctx) {
        Expression left = visit(ctx.expr(0));
        Expression right = visit(ctx.expr(1));
        if (ctx.op.getType() == FeatherweightJavaScriptParser.GT) {
            return new BinOpExpr(Op.GT, left, right);
        }
        else if (ctx.op.getType() == FeatherweightJavaScriptParser.LT) {
            return new BinOpExpr(Op.LT, left, right);
        }
        else if (ctx.op.getType() == FeatherweightJavaScriptParser.GTE) {
            return new BinOpExpr(Op.GE, left, right);
        }
        else if (ctx.op.getType() == FeatherweightJavaScriptParser.LTE) {
            return new BinOpExpr(Op.LE, left, right);
        }
        else {
            return new BinOpExpr(Op.EQ, left, right);
        }   
    }

    //funcApp
    @Override
    public Expression visitFuncApp(FeatherweightJavaScriptParser.FuncAppContext ctx) {
        String fName = ctx.ID().getText();
        VarExpr f = new VarExpr(fName);
        List<Expression> exps = new ArrayList<Expression>();
        for (int i = 0; i < ctx.expr().size(); i++) {
            exps.add(visit(ctx.expr(i)));
        }
        return new FunctionAppExpr(f, exps);
    }

    //funcDec
    @Override
    public Expression visitFuncDec(FeatherweightJavaScriptParser.FuncDecContext ctx) {
        List<String> params = new ArrayList<String>();
        Expression body = visit(ctx.block());
        int i = 0;
        TerminalNode id = ctx.ID(i);
        while (id != null) {
            params.add(id.getText());
            i += 1;
            id = ctx.ID(i);
        }
        return new FunctionDeclExpr(params, body);
    }

    //varDec
    @Override
    public Expression visitVarDec(FeatherweightJavaScriptParser.VarDecContext ctx) {
        String varName = String.valueOf(ctx.ID().getText());
        Expression exp = visit(ctx.expr());
        return new VarDeclExpr(varName, exp);
    }

    //assign
    @Override
    public Expression visitAssign(FeatherweightJavaScriptParser.AssignContext ctx) {
        String varName = String.valueOf(ctx.ID().getText());
        Expression exp = visit(ctx.expr());
        return new AssignExpr(varName, exp);
    }

    //ref
    @Override  
    public Expression visitRef(FeatherweightJavaScriptParser.RefContext ctx) {
        String varName = String.valueOf(ctx.ID().getText());
        return new VarExpr(varName);
    }

    @Override
    public Expression visitInt(FeatherweightJavaScriptParser.IntContext ctx) {
        int val = Integer.valueOf(ctx.INT().getText());
        return new ValueExpr(new IntVal(val));
    }

    //bool
    @Override
    public Expression visitBool(FeatherweightJavaScriptParser.BoolContext ctx) {
        boolean val = Boolean.valueOf(ctx.BOOL().getText());
        return new ValueExpr(new BoolVal(val));
    }

    //null
    @Override
    public Expression visitNull(FeatherweightJavaScriptParser.NullContext ctx) {
        return new ValueExpr(new NullVal());
    }

    @Override
    public Expression visitParens(FeatherweightJavaScriptParser.ParensContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Expression visitFullBlock(FeatherweightJavaScriptParser.FullBlockContext ctx) {
        List<Expression> stmts = new ArrayList<Expression>();
        for (int i=1; i<ctx.getChildCount()-1; i++) {
            Expression exp = visit(ctx.getChild(i));
            stmts.add(exp);
        }
        return listToSeqExp(stmts);
    }

    /**
     * Converts a list of expressions to one sequence expression,
     * if the list contained more than one expression.
     */
    private Expression listToSeqExp(List<Expression> stmts) {
        if (stmts.isEmpty()) return null;
        Expression exp = stmts.get(0);
        for (int i=1; i<stmts.size(); i++) {
            exp = new SeqExpr(exp, stmts.get(i));
        }
        return exp;
    }

    @Override
    public Expression visitSimpBlock(FeatherweightJavaScriptParser.SimpBlockContext ctx) {
        return visit(ctx.stat());
    }
}
