package org.grails.cli.profile.steps

import groovy.transform.CompileStatic
import org.grails.cli.profile.Command
import org.grails.cli.profile.ProfileCommand
import org.grails.cli.profile.Step

/**
 * Dynamic creation of {@link Step} instances
 *
 * @author rainboyan
 * @since 5.0
 */
@CompileStatic
class ModernStepFactory implements StepFactory {

    Map<String, Class<? extends Step>> steps = [
            createFile: CreateFileStep,
            insertFile: InsertFileStep,
            createLink: CreateLinkStep,
            run: RunStep,
            get: GetStep
    ]

    @Override
    Step createStep(String name, Command command, Map parameters) {
        if(command instanceof ProfileCommand) {
            return steps[name]?.newInstance(command, parameters)
        }
    }
}
