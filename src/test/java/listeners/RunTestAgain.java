package listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * Перезапуск неуспешного теста: TestNG вызывает retry() после падения,
 * true — запустить метод ещё раз, false — считать результат окончательным.
 * Актуально для живого внешнего API: Heroku-песочница периодически флакует.
 *
 * Подключается на методе: @Test(retryAnalyzer = RunTestAgain.class) —
 * TestNG создаёт отдельный экземпляр на каждый тест-метод, поэтому
 * счётчик retryCount не протекает между тестами.
 */
public class RunTestAgain implements IRetryAnalyzer {

    /** Сколько раз перезапускать неуспешный тест (не считая первого прогона). */
    private static final int MAX_RETRY_COUNT = 2;

    private int retryCount = 0;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (iTestResult.isSuccess() || retryCount >= MAX_RETRY_COUNT) {
            return false;
        }
        retryCount++;
        System.out.println("Retrying failed test: " + iTestResult.getName()
                + ", attempt " + (retryCount + 1) + " of " + (MAX_RETRY_COUNT + 1));
        return true;
    }
}
