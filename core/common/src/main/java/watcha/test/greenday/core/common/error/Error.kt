package watcha.test.greenday.core.common.error

sealed interface GreendayError {
    class ServerError(
        val message: String = "Sorry\n" +
                "we are having some\n" +
                "server issues",
        val subMessage: String = "We will do our best to fix it as soon as possible."
    ) : GreendayError

    class NetworkConnectionError(
        val message: String = "Please check\n" +
                "the Internet connection"
    ) : GreendayError
}