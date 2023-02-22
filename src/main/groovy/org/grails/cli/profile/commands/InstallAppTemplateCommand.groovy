package org.grails.cli.profile.commands

import groovy.transform.CompileStatic
import org.grails.build.parsing.CommandLine
import org.grails.cli.profile.Command
import org.grails.cli.profile.CommandDescription
import org.grails.cli.profile.ExecutionContext
import org.grails.cli.profile.ProjectCommand
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.SafeConstructor

@CompileStatic
class InstallAppTemplateCommand implements ProjectCommand {
    public static final String NAME = "install-app-template"

    CommandDescription description = new CommandDescription(name, "Install an application template", "grails install-app-template [LOCATION]=")

    @Override
    String getName() {
        return NAME
    }

    @Override
    boolean handle(ExecutionContext executionContext) {
        CommandLine commandLine = executionContext.commandLine
        String templateLocation = commandLine.remainingArgs ? commandLine.remainingArgs[0] : ""
        executionContext.console.addStatus("Install app template: location=" + templateLocation)

        def templateFile = new File(templateLocation)
        if (!templateFile.exists()) {
            executionContext.console.error("Template file " + templateLocation + 'not exists!')
        }
        FileInputStream is = new FileInputStream(templateFile)

        Map<String, Object> data = new Yaml(new SafeConstructor()).<Map>load(is)
        Command cmd = new TemplateMultiStepCommand("app-template", data)
        cmd.minArguments = 0

        ExecutionContext context = new AppTemplateExecutionContext()
        def result = cmd.handle(context)
        if (result) {
            executionContext.console.addStatus("Done.")
        } else {
            executionContext.console.addStatus("Error")
        }
        result
    }

}
