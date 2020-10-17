package com.example.demopostgresql.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demopostgresql.dto.CampaignSkuDetailDTO;
import com.example.demopostgresql.entity.CampaignSkuDetail;
import com.example.demopostgresql.services.CampaignService;

/**
 * @author rishi - created on 30/08/20
 **/
@RestController
@RequestMapping("/campaign")
public class CampaignController {

  @Autowired
  private CampaignService campaignService;

  @RequestMapping(method = RequestMethod.POST, value = "/addCampaignSkuDetail")
  public CampaignSkuDetailDTO addCampaignSkuDetail(@RequestBody CampaignSkuDetailDTO campaignSkuDetailDTO) {
    CampaignSkuDetail campaignSkuDetail = new CampaignSkuDetail();
    BeanUtils.copyProperties(campaignSkuDetailDTO, campaignSkuDetail);
    campaignService.addCampaignSkuDetail(campaignSkuDetail);
    return campaignSkuDetailDTO;
  }

  @RequestMapping(method = RequestMethod.GET, value = "/findByCampaignCode/{campaignCode}/{categoryCode}")
  public List<CampaignSkuDetail> getCampaignSkuDetailByCampaignCode(@PathVariable String campaignCode, @PathVariable
      String categoryCode) {
    List<CampaignSkuDetail> campaignSkuDetailList =
        campaignService.getCampaignSkuDetailByCampaignCode(campaignCode, categoryCode);
    return campaignSkuDetailList;
  }

  @PostMapping("/transaction/{campaignCode}/{quota}")
  public String transactionAnnotationExample(@PathVariable String campaignCode, @PathVariable int quota) {
    try {
      campaignService.transactionAnnotationExample(quota, campaignCode);
    } catch (Exception e) {
      return e.getMessage();
    }
    return "Database Updated";
  }

  @PutMapping("/delete/{campaignCode}")
  public String deleteByCampaignCode(@PathVariable String campaignCode) {
    campaignService.deleteByCampaignCode(campaignCode);
    return "DONE";
  }


  @PostMapping("/clearAutomaticallyTest")
  public CampaignSkuDetailDTO clearAutomaticallyTest(@RequestBody CampaignSkuDetailDTO campaignSkuDetailDTO) {
    CampaignSkuDetail campaignSkuDetail = new CampaignSkuDetail();
    BeanUtils.copyProperties(campaignSkuDetailDTO, campaignSkuDetail);
    campaignService.clearAutomaticallyTest(campaignSkuDetail);
    return campaignSkuDetailDTO;
  }
}
