package com.back.boundedContext.market.in;

import com.back.boundedContext.market.app.MarketFacade;
import com.back.shared.post.out.PostApiClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;

@Configuration
@Slf4j
public class MarketDataInit {
    private final MarketDataInit self;
    private final PostApiClient postApiClient;
    private final MarketFacade marketFacade;

    public MarketDataInit(@Lazy MarketDataInit marketDataInit,
                          MarketFacade marketFacade,
                          PostApiClient postApiClient) {
        this.self = marketDataInit;
        this.marketFacade = marketFacade;
        this.postApiClient = postApiClient;
    }

    @Bean
    @Order(3)
    public ApplicationRunner MaketDataInitApplicationRunner() {
        return args -> {
            self.makeBaseProducts();
        };
    }

    public void makeBaseProducts() {
        postApiClient.getItems()
                .forEach(postDto -> {
                    System.out.printf("id = %s name = %s%n", postDto.id(), postDto.authorName());
                });
    }
}
