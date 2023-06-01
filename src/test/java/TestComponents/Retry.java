package TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

    int count = 0;
    int maxTry = 1;

    @Override
    public boolean retry(ITestResult result) {
        if(count < maxTry){
            count++;
            return true; // to retry, you have to return true
        }
        return false;
    }
}
