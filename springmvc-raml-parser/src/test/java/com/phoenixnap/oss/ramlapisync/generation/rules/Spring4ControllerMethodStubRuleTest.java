package com.phoenixnap.oss.ramlapisync.generation.rules;

import com.phoenixnap.oss.ramlapisync.data.ApiMappingMetadata;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * @author armin.weisser
 * @since 0.3.2
 */
public class Spring4ControllerMethodStubRuleTest extends AbstractControllerRuleTestBase {

    @Test
    public void applyMethodRule_shouldCreate_validMethodSignature_withBody() throws JClassAlreadyExistsException {

        JDefinedClass jClass = jCodeModel.rootPackage()._class("Spring4ControllerTestStub");
        ApiMappingMetadata endpointMetadata = getControllerMetadata().getApiCalls().iterator().next();
        JMethod jMethod = new Spring4ControllerMethodStubRule().apply(endpointMetadata, jClass);

        assertThat(jMethod, is(notNullValue()));
        assertThat(jMethod.name(), equalTo("getBase"));
        assertThat(jMethod.mods().getValue(), equalTo(JMod.PUBLIC));
        assertThat(jMethod.type().name(), equalTo("ResponseEntity"));
        assertThat(jMethod.annotations(), hasSize(0)); // no implicit Annotations
        assertThat(jMethod.body().isEmpty(), is(false));
        assertThat(serializeModel(), containsString("return null; //TODO Autogenerated Method Stub. Implement me please."));
    }
}
