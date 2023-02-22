package org.grails.cli.profile.steps

import groovy.transform.CompileStatic
import org.grails.cli.profile.AbstractStep
import org.grails.cli.profile.CommandException
import org.grails.cli.profile.ExecutionContext
import org.grails.cli.profile.ProfileCommand
import org.grails.cli.profile.support.ArtefactVariableResolver

/**
 * A step that create a file
 *
 * @author rainboyan
 * @since 5.0
 */
@CompileStatic
class CreateLinkStep extends AbstractStep {

    public static final String NAME = "createLink"

    String location

    CreateLinkStep(ProfileCommand command, Map<String, Object> parameters) {
        super(command, parameters)
        location = parameters.location
    }

    @Override
    String getName() { NAME }

    @Override
    boolean handle(ExecutionContext context) {
        def console = context.console
        console.log "Create link: $location"
        return true
    }
}
