package ar.com.wolox.android.training.ui.home.news;

import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.facebook.drawee.backends.pipeline.Fresco;

import org.joda.time.DateTime;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.inject.Inject;

import ar.com.wolox.android.R;
import ar.com.wolox.android.training.model.News;
import ar.com.wolox.wolmo.core.fragment.WolmoFragment;
import butterknife.BindView;

public class NewsListFragment extends WolmoFragment<NewsListPresenter> implements INewsListView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.fragment_news_list_recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.fragment_news_list_swipe_container) SwipeRefreshLayout mSwipeLayout;
    private LinearLayoutManager mLayoutManager;
    private NewsListAdapter mNewsAdapter;

    @Inject public NewsListFragment() { }

    @Override
    public int layout() {
        return R.layout.fragment_home_news_list;
    }

    @Override
    public void init() {
        Fresco.initialize(getContext());

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // SwipeRefreshLayout
        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorAccent);

        /**
         * Showing Swipe Refresh animation on activity create
         * As animation won't start on onCreate, post runnable is used
         */
        mSwipeLayout.post(new Runnable() {

            @Override
            public void run() {
                loadNews();
            }
        });
    }

    @Override
    public void onRefresh() {
        loadNews();
    }

    public void loadNews() {
        mSwipeLayout.setRefreshing(true);

        News news1 = new News();
        news1.setTitle("¿Famosos y sólo amigos?");
        news1.setPicture("http://bucket1.glanacion.com/anexos/fotos/70/dia-del-amigo-2236070w620.jpg");
        news1.setText("Ser súper estrellas e íntimos amigos tiene sus desventajas, al menos para George. Su esposa, Amal, es muy celosa de Julia e irrumpió varias veces en las grabaciones de su última peli juntos, aunque nunca pescó nada raro.");
        news1.setCreatedAt(new DateTime(2018, 8, 14, 10, 59));
        news1.setLikes(Arrays.asList(1, 3));

        News news2 = new News();
        news2.setTitle("Hipnosis: la nueva vedette de las neurociencias");
        news2.setPicture("http://bucket1.glanacion.com/anexos/fotos/50/2082050.jpg");
        news2.setText("Hace un año, Marisa Bello, bibliotecóloga de La Plata, separada, 51 años, condujo un auto ultralujoso. Luego, durmió profundamente. Más tarde, se rió a carcajadas y olió un perfume indescriptible. Todo eso lo vivió desde una silla, apostada en el escenario de un pabellón de Tecnópolis. Para ella, sucedió durante una hora. \\\"En realidad, estuvo entre dieciséis y dieciocho minutos, que es el tiempo máximo que utilizamos durante nuestros espectáculos para hipnotizar a la gente -explica Gonzalo Blanc, un abogado de 41 años-. Pero la percepción del tiempo en ese estado es otra, y eso la llevó a sentir la experiencia mucho más larga\\\". Durante dieciséis presentaciones en Tecnópolis, Gonzalo, junto con el médico Daniel West, de 30 años, practicaron hipnosis colectiva sobre el público. Los dos viven en Montevideo y se dedican desde hace más de diez años a investigar las neurociencias. Dan seminarios, conferencias y talleres empresariales para mejorar el rendimiento a través de la hipnosis -tuvieron clientes como YPF, Telefónica, L'Oréal y Santillana-; practican hipnosis clínica para atenuar el dolor y curar patologías, y sus conferencias en TEDx Durazno y en TEDx Río de la Plata, llamadas \\\"¿Se puede entrenar a la mente para ser exitosos?\\\", tienen más de 150.000 reproducciones.");
        news2.setCreatedAt(new DateTime(2018, 8, 13, 23, 12));

        List<News> dataSet = new ArrayList<News>() {{
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
            add(news1);
            add(news2);
        }};

        mNewsAdapter = new NewsListAdapter(dataSet, getPresenter());
        mRecyclerView.setAdapter(mNewsAdapter);

        mSwipeLayout.setRefreshing(false);
    }
}
