window.onload = function() {
  //<editor-fold desc="Changeable Configuration Block">

  // the following lines will be replaced by docker/configurator, when it runs in a docker-container
  window.ui = SwaggerUIBundle({
    configUrl: "./openapi.yaml"
    url: "./openapi.yaml",
    dom_id: '#openapi',
    deepLinking: false,
    displayRequestDuration: true,
    layout: "BaseLayout"
  });

  //</editor-fold>
};
