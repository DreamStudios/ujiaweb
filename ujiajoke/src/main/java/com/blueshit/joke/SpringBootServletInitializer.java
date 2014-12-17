package com.blueshit.joke;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.builder.ParentContextApplicationContextInitializer;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext;
import org.springframework.boot.context.web.ServletContextApplicationContextInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Conventions;
import org.springframework.util.Assert;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.*;
import java.util.Arrays;
import java.util.EnumSet;

/**
 * A handy opinionated {@link org.springframework.web.WebApplicationInitializer} for applications that only have
 * one Spring servlet, and no more than a single filter (which itself is only enabled when
 * Spring Security is detected). If your application is more complicated consider using
 * one of the other WebApplicationInitializers.
 * <p>
 * Note that a WebApplicationInitializer is only needed if you are building a war file and
 * deploying it. If you prefer to run an embedded container (we do) then you won't need
 * this at all.
 *
 * @author Dave Syer
 */
public abstract class SpringBootServletInitializer implements WebApplicationInitializer {

    protected final Log logger = LogFactory.getLog(getClass());

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        beforeSpringSecurityFilterChain(servletContext);
        System.out.println("SpringBootServletInitializer onStartup!");
        WebApplicationContext rootAppContext = createRootApplicationContext(servletContext);
        if (rootAppContext != null) {
            servletContext.addListener(new ContextLoaderListener(rootAppContext) {
                @Override
                public void contextInitialized(ServletContextEvent event) {
                    // no-op because the application context is already initialized
                }
            });
        }
        else {
            this.logger.debug("No ContextLoaderListener registered, as "
                    + "createRootApplicationContext() did not "
                    + "return an application context");
        }
    }

    protected WebApplicationContext createRootApplicationContext(
            ServletContext servletContext) {
        ApplicationContext parent = null;
        Object object = servletContext
                .getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        if (object instanceof ApplicationContext) {
            this.logger.info("Root context already created (using as parent).");
            parent = (ApplicationContext) object;
            servletContext.setAttribute(
                    WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, null);
        }
        SpringApplicationBuilder application = new SpringApplicationBuilder();
        if (parent != null) {
            application.initializers(new ParentContextApplicationContextInitializer(
                    parent));
        }
        application.initializers(new ServletContextApplicationContextInitializer(
                servletContext));
        application.contextClass(AnnotationConfigEmbeddedWebApplicationContext.class);
        application = configure(application);
        // Ensure error pages are registered
        //application.sources(ErrorPageFilter.class);
        return (WebApplicationContext) application.run();
    }

    /**
     * Configure the application. Normally all you would need to do it add sources (e.g.
     * config classes) because other settings have sensible defaults. You might choose
     * (for instance) to add default command line arguments, or set an active Spring
     * profile.
     * @param application a builder for the application context
     * @see org.springframework.boot.builder.SpringApplicationBuilder
     */
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application;
    }

    protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {

    }

    protected final void insertFilters(ServletContext servletContext,Filter... filters) {
        registerFilters(servletContext, true, filters);
    }
    private void registerFilters(ServletContext servletContext, boolean insertBeforeOtherFilters, Filter... filters) {
        Assert.notEmpty(filters, "filters cannot be null or empty");

        for(Filter filter : filters) {
            if(filter == null) {
                throw new IllegalArgumentException("filters cannot contain null values. Got " + Arrays.asList(filters));
            }
            String filterName = Conventions.getVariableName(filter);
            registerFilter(servletContext, insertBeforeOtherFilters, filterName, filter);
        }
    }
    private final void registerFilter(ServletContext servletContext, boolean insertBeforeOtherFilters, String filterName, Filter filter) {
        FilterRegistration.Dynamic registration = servletContext.addFilter(filterName, filter);
        if(registration == null) {
            throw new IllegalStateException("Duplicate Filter registration for '" + filterName +"'. Check to ensure the Filter is only configured once.");
        }
        registration.setAsyncSupported(true);
        EnumSet<DispatcherType> dispatcherTypes = getSecurityDispatcherTypes();
        registration.addMappingForUrlPatterns(dispatcherTypes, !insertBeforeOtherFilters, "/*");
    }

    protected EnumSet<DispatcherType> getSecurityDispatcherTypes() {
        return EnumSet.of(DispatcherType.REQUEST, DispatcherType.ERROR, DispatcherType.ASYNC);
    }
}
