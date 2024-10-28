package ch01_calculator.text;

public final class InfoText {

	public static final String APPLICATION_START_UP_MESSAGE = "Java 계산기에 오신 것을 환영합니다!";

	public static final String COMMAND_EXPLAIN_MESSAGE = """
		아래의 모드 중 하나를 선택해주세요.
		0. 종료하기
		1. 간단 사칙연산 계산기
		""";
	public static final String SIMPLE_CALCULATOR_MODE_SELECT_MESSAGE = """
		간편 사칙연산 계산기를 선택하셨습니다.
		현재 지원되는 연산은 +, - , *, / 입니다.
		연산자 우선순위에 상관없이 입력된 순서대로 계산합니다.
		""";
	public static final String END_MODE_SELECT_MESSAGE = """
		종료를 선택하셨습니다.
		Java 계산기를 종료합니다...
		""";

	public static final String COMMAND_ENTER_MESSAGE = "식을 입력해주세요 : ";
}
