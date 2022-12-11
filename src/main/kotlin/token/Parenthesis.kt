package token

sealed interface Parenthesis {
    companion object {
        object LeftParenthesis : Parenthesis {
            override fun toString(): String = "LEFT"
        }

        object RightParenthesis : Parenthesis {
            override fun toString(): String = "RIGHT"
        }
    }
}