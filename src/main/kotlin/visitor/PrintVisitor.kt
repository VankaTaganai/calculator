package visitor

import token.Token.Companion.ParenthesisToken
import token.Token.Companion.NumberToken
import token.Token.Companion.OperationToken

class PrintVisitor : TokenVisitor {
    override fun visit(token: NumberToken) {
        print(" $token ")
    }

    override fun visit(token: ParenthesisToken) {
        print(" $token ")
    }

    override fun visit(token: OperationToken) {
        print(" $token ")
    }
}