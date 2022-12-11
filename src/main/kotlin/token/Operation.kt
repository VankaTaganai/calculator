package token

sealed interface Operation {
    companion object {
        object Plus : Operation {
            override fun toString(): String = "PLUS"
        }

        object Minus : Operation {
            override fun toString(): String = "MINUS"
        }

        object Mul : Operation {
            override fun toString(): String = "PLUS"
        }

        object Div : Operation {
            override fun toString(): String = "DIV"
        }
    }
}