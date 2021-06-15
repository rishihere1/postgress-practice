package com.example.demopostgresql.dto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.example.demopostgresql.entity.Campaign;
import com.example.demopostgresql.entity.CampaignMerchantDetail;
import com.example.demopostgresql.entity.CampaignMerchantDetail_;
import com.example.demopostgresql.entity.CampaignRuleEntityCode;
import com.example.demopostgresql.entity.CampaignRuleEntityCode_;
import com.example.demopostgresql.entity.CampaignRuleSet;
import com.example.demopostgresql.entity.CampaignRuleSet_;
import com.example.demopostgresql.entity.Campaign_;
import com.example.demopostgresql.repository.RuleType;

@Service
public class CampaignCriteriaBuilder implements Specification<Campaign> {

  // Specification criteria query being used in filter queries where some parameters are ignored and some added based on input from user

  private static final String CAMPAIGN_RULE_MERCHANT = "campaignRuleSetMerchantSelect";
  private static final String DATE_PATTERN = "dd-MM-yyyy";
  private static final String TO_CHAR_CRITERIA_FUNCTION = "to_char";
  private static final String LIKE_OPERATOR = "%";
  private static final String CAMPAIGN_RULE_SETS = "campaignRuleSets";
  private static final String CAMPAIGN_RULE_ENTITY_CODES = "ruleEntityCodes";
  private static final String CAMPAIGN_CODE = "campaignCode";
  private static final String CAMPAIGN_RULE_SET_NOT_IGNORED = "CampaignRuleSetNotIgnored";
  private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(DATE_PATTERN);
  private static final String CAMPAIGN_CODE_LIST_RULE = "campaignCodeListRule";
  private CampaignFilterRequest campaignFilterRequest;
  private List<String> campaignCodeList = new ArrayList<>();

  public void setCampaignFilterRequest(CampaignFilterRequest campaignFilterRequest) {
    this.campaignFilterRequest = campaignFilterRequest;
  }

  @Override
  public Predicate toPredicate(Root<Campaign> root, CriteriaQuery<?> criteriaQuery,
      CriteriaBuilder criteriaBuilder) {
    List<Predicate> predicateList = new ArrayList<>();
    Map<Object, Predicate> predicateMap = new HashMap<>(joinTables(root, criteriaBuilder, criteriaQuery));
    for (Map.Entry<Object, Predicate> mapEntry : predicateMap.entrySet()) {
      if (!ObjectUtils.isEmpty(mapEntry.getKey())) {
        predicateList.add(mapEntry.getValue());
      }
    }
//    predicateList.add(criteriaBuilder.greaterThanOrEqualTo(root.get(Campaign_.perfectTransaction), 20));
    List<Order> orders = new ArrayList<>();
    criteriaQuery.orderBy(orders);
    criteriaQuery.distinct(true);
    return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
  }

  private Map<Object, Predicate> joinTables(Root<Campaign> campaignRoot, CriteriaBuilder criteriaBuilder,
      CriteriaQuery<?> criteriaQuery) {
    Map<Object, Predicate> predicateMap = new HashMap<>();

    Join<Campaign, CampaignRuleSet> campaignRuleSetJoin =
        campaignRoot.joinSet(CAMPAIGN_RULE_SETS);
    Join<CampaignRuleSet, CampaignRuleEntityCode> entityCodeJoin =
        campaignRuleSetJoin.joinList(CAMPAIGN_RULE_ENTITY_CODES, JoinType.LEFT);
    Join<Campaign, CampaignMerchantDetail> campaignCampaignMerchantDetailJoin =
        campaignRoot.joinSet("campaignMerchantDetails", JoinType.LEFT);

    Predicate merchantRuleSelect = criteriaBuilder
        .equal(campaignRuleSetJoin.get(CampaignRuleSet_.ruleType), RuleType.MERCHANT);

    Predicate merchantIgnored =
        criteriaBuilder.equal(campaignRuleSetJoin.get(CampaignRuleSet_.isIgnored), true);

    Predicate sellerRatingNull = criteriaBuilder.isNull(campaignRoot.get(Campaign_.sellerRating));

    Predicate sellerRatingCheck =
        criteriaBuilder.lessThanOrEqualTo(campaignRoot.get(Campaign_.sellerRating), campaignFilterRequest.getSellerRating());

    Predicate merchantCodeCheck = criteriaBuilder
        .equal(entityCodeJoin.get(CampaignRuleEntityCode_.entityCode), campaignFilterRequest.getMerchantCode());

    Predicate merchantCodeRegistered = criteriaBuilder
        .equal(campaignCampaignMerchantDetailJoin.get(CampaignMerchantDetail_.merchantCode),
            campaignFilterRequest.getMerchantCode());

    Predicate finalPredicate = criteriaBuilder
        .or(criteriaBuilder.and(sellerRatingNull, criteriaBuilder.or(merchantIgnored, merchantCodeCheck)),
            criteriaBuilder.or(sellerRatingCheck, merchantCodeRegistered));

    predicateMap.put("s", finalPredicate);
    predicateMap.put("ss", merchantRuleSelect);

    return predicateMap;
  }

//  private Map<Object, Predicate> getQueryPredicateMap(Root<Campaign> campaignRoot,
//      CriteriaBuilder criteriaBuilder) {
//    Map<Object, Predicate> predicateMap = new HashMap<>();
//    String campaignCode = campaignFilterRequest.getCampaignCode();
//    if ((campaignCode == null)) {
//      predicateMap.put(campaignCode, getCampaignCodePredicate(campaignRoot, criteriaBuilder));
//    }
//    return predicateMap;
//  }

  private Predicate getCampaignCodePredicate(Root<Campaign> campaignRoot,
      CriteriaBuilder criteriaBuilder) {
    StringBuilder builder =
        new StringBuilder(LIKE_OPERATOR).append(campaignFilterRequest.getCampaignCode().toUpperCase())
            .append(LIKE_OPERATOR);
    return criteriaBuilder.like(criteriaBuilder.upper(campaignRoot.get(Campaign_.campaignCode)), builder.toString());
  }

}
