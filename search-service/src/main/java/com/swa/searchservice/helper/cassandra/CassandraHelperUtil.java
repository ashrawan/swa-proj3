package com.swa.searchservice.helper.cassandra;

import com.datastax.oss.driver.api.core.cql.PagingState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;

import java.nio.ByteBuffer;

@Slf4j
public class CassandraHelperUtil {

    public static CassandraPageRequest createCassandraPageRequest(final Integer limit, final String pagingState) {
        final PageRequest pageRequest = PageRequest.of(0, limit);
        final PagingState pageState = StringUtils.hasText(pagingState) ? PagingState.fromString(pagingState) : null;
        ByteBuffer rawPagingState = pageState != null ? pageState.getRawPagingState() : null;
        CassandraPageRequest cassandraPageRequest = CassandraPageRequest.of(pageRequest, rawPagingState);
        log.info("Initialized Cassandra Page Request {}", cassandraPageRequest);
        return cassandraPageRequest;
    }
}
