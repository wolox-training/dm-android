package ar.com.wolox.android.training.ui.home.news.list;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import ar.com.wolox.android.training.model.News;
import ar.com.wolox.android.training.network.NewsService;
import ar.com.wolox.android.training.ui.errors.ErrorCode;
import ar.com.wolox.android.training.utils.UserSession;
import ar.com.wolox.wolmo.core.presenter.BasePresenter;
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices;
import ar.com.wolox.wolmo.networking.retrofit.callback.NetworkCallback;
import okhttp3.ResponseBody;

public class NewsListPresenter extends BasePresenter<INewsListView> {

    // Constants

    // Variables
    private UserSession mUserSession;
    @Inject RetrofitServices mRetrofitServices;

    // Constructor
    @Inject
    public NewsListPresenter(UserSession userSession) { mUserSession = userSession; }

    public Integer getUserId() {
        return mUserSession.getId();
    }

    public void getNews(int page, int limit) {

        mRetrofitServices.getService(NewsService.class).list(page, limit).enqueue(new NetworkCallback<List<News>>() {
            @Override
            public void onResponseSuccessful(List<News> news) {
                news = getTestNews();
                if (news.size() > 0) {
                    getView().onGetNewsSuccess(news);

                    if (news.size() < limit) {
                        getView().onGetNewsLastPage();
                    }
                } else {
                    getView().onGetNewsError(ErrorCode.NO_NEWS);
                }
            }

            @Override
            public void onResponseFailed(ResponseBody responseBody, int code) {
                switch (code) {
                    case 400:
                        getView().onGetNewsLastPage();
                        break;

                    default:
                        getView().onGetNewsError(ErrorCode.INERNET_CONNECTION_ERROR);
                        break;
                }
            }

            @Override
            public void onCallFailure(Throwable t) {
                getView().onGetNewsError(ErrorCode.INERNET_CONNECTION_ERROR);
            }
        });
    }

    public void getLastNews(Integer idLast, Integer limit) {
        mRetrofitServices.getService(NewsService.class).list(1, limit).enqueue(new NetworkCallback<List<News>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponseSuccessful(List<News> news) {
                if (news.stream().anyMatch(n -> n.getId().equals(idLast))) {
                    news = news.subList(0,  news.indexOf(news.stream().filter(n -> n.getId().equals(idLast)).findFirst().get()));
                }

                if (news.size() > 0) {
                    getView().onGetLastNewsSuccess(news);
                } else {
                    getView().completeLoading();
                }
            }

            @Override
            public void onResponseFailed(ResponseBody responseBody, int code) {
                switch (code) {
                    case 400:
                        getView().completeLoading();
                        break;

                    default:
                        getView().onGetNewsError(ErrorCode.INERNET_CONNECTION_ERROR);
                        break;
                }
            }

            @Override
            public void onCallFailure(Throwable t) {
                getView().onGetNewsError(ErrorCode.INERNET_CONNECTION_ERROR);
            }
        });
    }

    private List<News> getTestNews() {
        News news1 = new News();
        news1.setId(-1);
        news1.setTitle("aaaa¿Famosos y sólo amigos?");
        news1.setPicture("http://bucket1.glanacion.com/anexos/fotos/70/dia-del-amigo-2236070w620.jpg");
        news1.setText("Ser súper estrellas e íntimos amigos tiene sus desventajas, al menos para George. Su esposa, Amal, es muy celosa de Julia e irrumpió varias veces en las grabaciones de su última peli juntos, aunque nunca pescó nada raro.");
        news1.setCreatedAt(new DateTime(2018, 8, 14, 10, 59));
        news1.setLikes(Arrays.asList(1, 3));

        News news2 = new News();
        news2.setId(-2);
        news2.setTitle("bbbbbHipnosis: la nueva vedette de las neurociencias");
        news2.setPicture("http://bucket1.glanacion.com/anexos/fotos/50/2082050.jpg");
        news2.setText("Hace un año, Marisa Bello, bibliotecóloga de La Plata, separada, 51 años, condujo un auto ultralujoso. Luego, durmió profundamente. Más tarde, se rió a carcajadas y olió un perfume indescriptible. Todo eso lo vivió desde una silla, apostada en el escenario de un pabellón de Tecnópolis. Para ella, sucedió durante una hora. \\\"En realidad, estuvo entre dieciséis y dieciocho minutos, que es el tiempo máximo que utilizamos durante nuestros espectáculos para hipnotizar a la gente -explica Gonzalo Blanc, un abogado de 41 años-. Pero la percepción del tiempo en ese estado es otra, y eso la llevó a sentir la experiencia mucho más larga\\\". Durante dieciséis presentaciones en Tecnópolis, Gonzalo, junto con el médico Daniel West, de 30 años, practicaron hipnosis colectiva sobre el público. Los dos viven en Montevideo y se dedican desde hace más de diez años a investigar las neurociencias. Dan seminarios, conferencias y talleres empresariales para mejorar el rendimiento a través de la hipnosis -tuvieron clientes como YPF, Telefónica, L'Oréal y Santillana-; practican hipnosis clínica para atenuar el dolor y curar patologías, y sus conferencias en TEDx Durazno y en TEDx Río de la Plata, llamadas \\\"¿Se puede entrenar a la mente para ser exitosos?\\\", tienen más de 150.000 reproducciones.");
        news2.setCreatedAt(new DateTime(2018, 8, 13, 23, 12));

        return new ArrayList<News>() {{
            add(news1);
            add(news2);
            add(news1);
            add(news2);
            add(news1);
            add(news2);
            add(news1);
            add(news2);
            add(news1);
            add(news2);
        }};
    }
}
