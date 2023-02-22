package org.grails.cli.profile.commands

import grails.build.logging.GrailsConsole
import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import org.grails.build.parsing.CommandLine
import org.grails.cli.profile.CommandCancellationListener
import org.grails.cli.profile.ExecutionContext
import org.grails.cli.profile.ProjectContext

/**
 * AppTemplateExecutionContext
 *
 * @author rainboyan
 * @since 5.0
 */
@CompileStatic
class AppTemplateExecutionContext implements ExecutionContext {
    CommandLine commandLine
    @Delegate(excludes = ['getConsole', 'getBaseDir']) ProjectContext projectContext
    GrailsConsole console = GrailsConsole.getInstance()

    @Override
    void cancel() {

    }

    @Override
    void addCancelledListener(CommandCancellationListener listener) {

    }

    @Override
    File getBaseDir() {
        return null
    }
}