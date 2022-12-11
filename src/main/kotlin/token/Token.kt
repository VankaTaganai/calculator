package token

import visitor.TokenVisitor

sealed interface Token {

    fun accept(visitor: TokenVisitor)

    companion object {
        data class NumberToken(val value: Int) : Token {
            override fun toString(): String = "NUMBER($value)"

            override fun accept(visitor: TokenVisitor) = visitor.visit(this)
        }

        data class OperationToken(val operation: Operation): Token {
            override fun toString(): String = operation.toString()

            override fun accept(visitor: TokenVisitor) = visitor.visit(this)
        }

        data class ParenthesisToken(val parenthesis: Parenthesis) : Token {
            override fun toString(): String = parenthesis.toString()

            override fun accept(visitor: TokenVisitor) = visitor.visit(this)
        }
    }
}