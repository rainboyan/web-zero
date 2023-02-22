package org.grails.cli.profile.commands

import grails.build.logging.GrailsConsole
import groovy.transform.CompileDynamic
import org.grails.cli.profile.*
import org.grails.cli.profile.steps.StepRegistry

/**
 * Simple implementation of the {@link MultiStepCommand} abstract class that parses commands defined in YAML or JSON
 *
 * @author rainboyan
 * @since 5.0
 */
class TemplateMultiStepCommand extends MultiStepCommand {
    private Map<String, Object> data
    private List<AbstractStep> steps

    final CommandDescription description

    TemplateMultiStepCommand(String name, Map<String, Object> data) {
        super(name, null)
        this.data = data

        def description = data?.description
        if(description instanceof List) {
            List descList = (List)description
            if(descList) {

                this.description = new CommandDescription(name: name, description: descList.get(0).toString(), usage: data?.usage)

                if(descList.size()>1) {
                    for(arg in descList[1..-1]) {
                        if(arg instanceof Map) {
                            Map map = (Map)arg
                            if(map.containsKey('usage')) {
                                this.description.usage = map.get('usage')?.toString()
                            }
                            else {
                            }
                        }
                    }
                }
            }
        }
        else {
            this.description = new CommandDescription(name: name, description: description.toString(), usage: data?.usage)
        }
    }

    List<Step> getSteps() {
        if(steps==null) {
            steps = []
            data.steps?.each { 
                Map<String, Object> stepParameters = it.collectEntries { k,v -> [k as String, v] }
                AbstractStep step = createStep(stepParameters)
                if (step != null) {
                    steps.add(step)
                }
            }
        }
        steps
    }

    protected AbstractStep createStep(Map stepParameters) {
        StepRegistry.getStep(stepParameters.command?.toString(), this, stepParameters)
    }

}
