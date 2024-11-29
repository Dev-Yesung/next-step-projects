package ch01_calculator.mode.executor;

import ch01_calculator.operand.Operand;

public interface Executor {

	Operand<?> execute();
}
