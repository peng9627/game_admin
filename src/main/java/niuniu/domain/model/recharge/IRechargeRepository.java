package niuniu.domain.model.recharge;

import niuniu.infrastructure.persistence.hibernate.generic.IHibernateGenericRepository;
import org.hibernate.criterion.Criterion;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by pengyi on 16-7-9.
 */
public interface IRechargeRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {

    BigDecimal total(List<Criterion> criterionList, Map<String, String> aliasMap);


    T byRechargeNo(String rechargeNo);
}
