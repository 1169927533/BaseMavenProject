import org.gradle.api.Plugin
import org.gradle.api.Project

public class DependPlugin implements Plugin<Project>{
    @Override
    void apply(Project project) {
        project.extensions.create("depend",DependComp)
    }
}