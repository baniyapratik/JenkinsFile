import hudson.model.*
import java.util.regex.*;

def zip_file_matcher = manager.getLogMatcher("(.*/allure/.*\\.xml)\$")
def reg_matcher = manager.getLogMatcher("Registration I[dD]\\s*:\\s*(.*)\$")
if(zip_file_matcher?.matches()) {
    def zip_file = zip_file_matcher.group(1)
    manager.listener.logger.println("zip_file: " + zip_file)

    def reg_id = ''
    if (reg_matcher?.matches()) {
      reg_id = reg_matcher.group(1)
    }
    manager.listener.logger.println("reg_id: " + reg_id)

    def pa= new ParametersAction([
        new StringParameterValue("ZIP_FILE", zip_file),
        new StringParameterValue("REG_ID", reg_id)
    ])
    manager.build.actions.add(pa)
}
