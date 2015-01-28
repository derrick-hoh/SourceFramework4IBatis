package com.sourceframework.web.binder;

import java.util.Collection;
import org.springframework.util.Assert;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

public class CustomBindingInitializer
  implements WebBindingInitializer
{
  private Collection<WebBindingInitializer> webBindingInitializers;

  public void setWebBindingInitializers(Collection<WebBindingInitializer> webBindingInitializers)
  {
    this.webBindingInitializers = webBindingInitializers;
  }

  public void initBinder(WebDataBinder binder, WebRequest request) {
    Assert.notEmpty(this.webBindingInitializers, "WebBindingInitializer Collection is null!");

    for (WebBindingInitializer webBindingInitializer : this.webBindingInitializers)
      webBindingInitializer.initBinder(binder, request);
  }
}