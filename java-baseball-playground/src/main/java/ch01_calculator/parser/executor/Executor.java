package ch01_calculator.parser.executor;

import ch01_calculator.operand.Operand;

public interface Executor {

	Operand<?> execute();
}
