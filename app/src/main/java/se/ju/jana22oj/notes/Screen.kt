package se.ju.jana22oj.notes

sealed class Screen(val route: String)
{
    object MScreen : Screen("m_screen")
    object DetailScreen : Screen("detail_screen")

    fun  withArgs(vararg  args: String) : String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}

