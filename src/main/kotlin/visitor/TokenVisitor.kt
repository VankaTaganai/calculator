package visitor

import token.Token.Companion.ParenthesisToken
import token.Token.Companion.NumberToken
import token.Token.Companion.OperationToken

interface TokenVisitor {

    fun visit(token: NumberToken)

    fun visit(token: ParenthesisToken)

    fun visit(token: OperationToken)

}