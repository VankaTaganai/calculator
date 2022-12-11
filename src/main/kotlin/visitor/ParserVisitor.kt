package visitor

import token.Token
import java.util.*
import token.Token.Companion.NumberToken
import token.Token.Companion.ParenthesisToken
import token.Token.Companion.OperationToken
import token.Parenthesis.Companion.LeftParenthesis
import token.Parenthesis.Companion.RightParenthesis
import token.Operation

import token.Operation.Companion.Plus
import token.Operation.Companion.Minus
import token.Operation.Companion.Mul
import token.Operation.Companion.Div

class ParserVisitor : TokenVisitor {
    private val deque: Deque<Token>        = ArrayDeque()
    private val result: MutableList<Token> = mutableListOf()

    override fun visit(token: NumberToken) {
        result.add(token)
    }

    fun getResult(): List<Token> {
        while (deque.isNotEmpty()) {
            when (val token = deque.removeLast()) {
                is OperationToken -> result.add(token)
                else              -> throw IllegalStateException("Unexpected token: $token")
            }
        }
        return result
    }

    override fun visit(token: ParenthesisToken) {
        when (token.parenthesis) {
            is LeftParenthesis  -> deque.addLast(token)
            is RightParenthesis -> {
                while (deque.isNotEmpty()) {
                    when (val t = deque.removeLast()) {
                        is OperationToken   -> result.add(t)
                        is ParenthesisToken -> {
                            when (t.parenthesis) {
                                is LeftParenthesis  -> break
                                is RightParenthesis -> throw IllegalStateException("Unexpected token: $token")
                            }
                        }
                        is NumberToken -> throw IllegalStateException("Unexpected token: $token")
                    }
                }
            }
        }
    }

    override fun visit(token: OperationToken) {
        while (deque.isNotEmpty()) {
            when (val t = deque.last) {
                is OperationToken -> {
                    if (getPriority(t.operation) >= getPriority(token.operation)) {
                        result.add(t)
                        deque.removeLast()
                    } else {
                        break
                    }
                }
                else -> break
            }
        }
        deque.addLast(token)
    }

    companion object {
        fun getPriority(operation: Operation): Int =
            when (operation) {
                is Plus, is Minus -> 1
                is Mul, is Div    -> 2
            }
    }
}