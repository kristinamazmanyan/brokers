package com.brokerexam.repository.event;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.brokerexam.bean.event.EventListBean;
import com.brokerexam.bean.event.SearchEventBean;
import com.brokerexam.common.bean.PagingResult;
import com.brokerexam.common.util.TextUtils;
import com.brokerexam.domain.event.Event;
import com.brokerexam.repository.AbstractDaoImpl;

@Repository("eventDao")
@Lazy
public class EventDaoImpl extends AbstractDaoImpl implements EventDao {

	private EventLisBeanMapper eventListBeanMapper = new EventLisBeanMapper();
	
	private static final Map<String, String> sortByColumnMap = new HashMap<String, String>();

	static {
		sortByColumnMap.put("eventType", "e.event_type AAA, e.id ");		
		sortByColumnMap.put("message", "e.message AAA, e.id ");		
		sortByColumnMap.put("performedBy", "u.login AAA, e.id ");		
		sortByColumnMap.put("performedOn", "e.performed_on AAA, e.id ");
		sortByColumnMap.put("createdAt", "e.created_at AAA, e.id ");		
	}

	@Autowired
	EventDaoImpl(DataSource dataSource) {
		super(dataSource);
	}

	private static final String saveEventSql = "insert into event (id, event_type, message, performed_by, performed_on, created_at) "
			+ " values(:id, :event_type, :message, :performed_by, :performed_on, now())";

	@Override
	public void saveEvent(Event event) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", 0);
		params.put(EventMapper.EVENT_TYPE, event.getEventType());
		params.put(EventMapper.MESSAGE, event.getMessage());
		params.put(EventMapper.PERFORMED_BY, event.getPerformedBy());
		params.put(EventMapper.PERFORMED_ON, event.getPerformedOn());
		parameterJdbcTemplate.update(saveEventSql, params);
	}

	private static final String searchCommonSql = "select {columns} "
			+ "from event e " + "left join user u on u.id = e.performed_by "
			+ "where 1=1 ";
	private static final String columnsSql = "distinct e.id, e.event_type, e.message, u.login as performed_by, e.performed_on, e.created_at ";

	@Override
	public PagingResult<EventListBean> search(SearchEventBean eventBean,
			int startRowIndex, int rowCount, String sortBy, boolean ascending) {
		PagingResult<EventListBean> pagingResult = new PagingResult<EventListBean>();
		Map<String, Object> params = new HashMap<String, Object>();
		String whereClause = createtWhereClouse(eventBean, params);

		String searchSql = searchCommonSql;
		searchSql = searchSql.replace("{columns}", columnsSql);

		StringBuilder limit = new StringBuilder();
		limit.append(" limit ").append(rowCount);
		limit.append(" offset ").append(startRowIndex);


		StringBuilder finalSql = new StringBuilder();
		finalSql.append(searchSql);
		finalSql.append(whereClause);
		finalSql.append(createOrderByClause(sortBy, ascending));
		finalSql.append(limit.toString());

		List<EventListBean> list = parameterJdbcTemplate.query(
				finalSql.toString(), params, eventListBeanMapper);

		searchSql = searchCommonSql;
		searchSql = searchSql.replace("{columns}", "count(e.id) as cnt");

		finalSql = new StringBuilder();
		finalSql.append(searchSql);
		finalSql.append(whereClause);

		long count = parameterJdbcTemplate.queryForObject(finalSql.toString(),
				params, Long.class);

		pagingResult.setTotalRows(count);
		pagingResult.setList(list);
		return pagingResult;
	}

	private String createtWhereClouse(SearchEventBean eventBean,
			Map<String, Object> params) {
		StringBuilder where = new StringBuilder();

		if (eventBean.getPerformedBy() != 0) {
			where.append(" and e.performed_by = :performed_by");
			params.put("performed_by", eventBean.getPerformedBy());
		}

		if (eventBean.getCreatedAtStart() != null) {
			where.append(" and e.created_at >= :createdAtStart");
			params.put("createdAtStart", eventBean.getCreatedAtStart());
		}
		if (eventBean.getCreatedAtEnd() != null) {
			where.append(" and e.created_at <= :createdAtEnd");
			params.put("createdAtEnd", eventBean.getCreatedAtEnd());
		}
		return where.toString();
	}

	private String createOrderByClause(String sortBy, boolean ascending) {
		String result = "";

		if (TextUtils.hasText(sortBy)) {
			String sortByCol = sortByColumnMap.get(sortBy);

			if (TextUtils.hasText(sortByCol)) {
				result += " order by " + sortByCol;

				if (ascending) {
					result = result.replaceAll("AAA", "");
				} else {
					result = result.replaceAll("AAA", "desc");
				}
			}
		}

		return result;
	}

}
