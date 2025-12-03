
package ai.neodatagroup.themis.bot;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.lumo.Lumo;

@Theme(themeClass = Lumo.class, variant = Lumo.LIGHT)
@PWA(name = "Themis Chat", shortName = "Themis Chat")
public class AppShell implements AppShellConfigurator {}
