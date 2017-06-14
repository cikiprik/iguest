
import java.util.LinkedHashMap;
import java.util.Map;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Messagebox.ClickEvent;

public class NavigationViewModel {

    Session session = Executions.getCurrent().getDesktop().getSession();
    NavigationPage currentPage;
    private Map<String, Map<String, NavigationPage>> pageMap;

    @Init
    public void init() {
        if (session.getAttribute("authName") != null && session.getAttribute("authRole") != null) {
            initPageMap((int) session.getAttribute("authRole"));
            currentPage = pageMap.get("iGuest").get("About Us");
        } else {
            Executions.sendRedirect("index.zul");
        }

    }

    @Command
    public void navigatePage(@BindingParam("target") NavigationPage targetPage) {
        BindUtils.postNotifyChange(null, null, currentPage, "selected");
        currentPage = targetPage;
        BindUtils.postNotifyChange(null, null, this, "currentPage");
    }

    public NavigationPage getCurrentPage() {
        return currentPage;
    }

    public Map<String, Map<String, NavigationPage>> getPageMap() {
        return pageMap;
    }

    private void initPageMap(int role) {
        pageMap = new LinkedHashMap<String, Map<String, NavigationPage>>();
        // Room Service
        addPage("iGuest", "About Us", "/home/about_us.zul");
        if (role == 1 || role == 2) {
            // super user & manager
            addPage("iGuest", "User Data", "/home/menu.zul");
            addPage("iGuest", "Rooms Data", "/home/faq.zul");
            addPage("iGuest", "Employee Data", "/home/press.zul");
            addPage("iGuest", "Guest Data", "/home/press.zul");

            addPage("Room", "Reserve", "/customers/customers.zul", "active");
            addPage("Room", "Check in", "/customers/customers.zul", "active");
            addPage("Room", "Check out", "/customers/customers.zul", "active");
            addPage("Room", "Status Check", "/customers/customers.zul", "inactive");

            addPage("Guest", "Register", "/fan_service/events.zul");
            addPage("Guest", "Requests", "/fan_service/promotion.zul");
            addPage("Guest", "Payments", "/fan_service/promotion.zul");

            addPage("Reports", "Bookings/Availability", "/orders/orders.zul", "in-preparation");
            addPage("Reports", "Arrival/Departure ", "/orders/orders.zul", "ready");
            addPage("Reports", "Sales", "/orders/orders.zul", "shipping");
            addPage("Reports", "General", "/orders/orders.zul", "later");
        }

        if (role == 3) {
            // front office

            addPage("iGuest", "Guest Data", "/home/press.zul");

            addPage("Room", "Reserve", "/customers/customers.zul", "active");
            addPage("Room", "Check in", "/customers/customers.zul", "active");
            addPage("Room", "Check out", "/customers/customers.zul", "active");
            addPage("Room", "Status Check", "/customers/customers.zul", "inactive");

            addPage("Guest", "Register", "/fan_service/events.zul");
            addPage("Guest", "Requests", "/fan_service/promotion.zul");
            addPage("Guest", "Payments", "/fan_service/promotion.zul");

            addPage("Reports", "General", "/orders/orders.zul", "later");
        }

        if (role == 4) {

            addPage("Room", "Status Check", "/customers/customers.zul", "inactive");

            addPage("Guest", "Requests", "/fan_service/promotion.zul");

            addPage("Reports", "General", "/orders/orders.zul", "later");
        }

    }

    private void addPage(String title, String subTitle, String includeUri) {
        addPage(title, subTitle, includeUri, null);
    }

    @Command
    public void doLogout() {

        Messagebox.show("Are you sure you want to Log Out?", "Log Out", new Messagebox.Button[]{
            Messagebox.Button.YES, Messagebox.Button.NO}, Messagebox.QUESTION, clickListener);

    }

    EventListener<ClickEvent> clickListener = new EventListener<Messagebox.ClickEvent>() {
        @Override
        public void onEvent(ClickEvent event) throws Exception {
            if (event.getName().equals("onYes")) {
                System.out.println("Log Out");
                session.getAttributes().clear();
                Executions.getCurrent().getSession().invalidate();
                Executions.getCurrent().sendRedirect("index.zul");
            }

        }
    };

    private void addPage(String title, String subTitle, String includeUri, String data) {
        String folder = "/widgets/menu/navbar/pages";
        Map<String, NavigationPage> subPageMap = pageMap.get(title);
        if (subPageMap == null) {
            subPageMap = new LinkedHashMap<String, NavigationPage>();
            pageMap.put(title, subPageMap);
        }
        NavigationPage navigationPage = new NavigationPage(title, subTitle,
                folder + includeUri + "?random=" + Math.random(), data) {
            @Override
            public boolean isSelected() {
                return currentPage == this;
            }
        };
        subPageMap.put(subTitle, navigationPage);
    }
}