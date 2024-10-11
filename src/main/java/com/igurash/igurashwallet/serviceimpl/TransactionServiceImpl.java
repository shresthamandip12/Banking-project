/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.igurash.igurashwallet.serviceimpl;

import com.igurash.igurashwallet.entity.Donation;
import com.igurash.igurashwallet.entity.ServiceStatustable;
import com.igurash.igurashwallet.entity.Transaction;
import com.igurash.igurashwallet.entity.User;
import com.igurash.igurashwallet.entity.Wallet;
import com.igurash.igurashwallet.repository.DonationRepositoy;
import com.igurash.igurashwallet.repository.ServiceStatusRepository;
import com.igurash.igurashwallet.repository.TransactionRepository;
import com.igurash.igurashwallet.repository.UserRepository;
import com.igurash.igurashwallet.repository.WalletRepository;
import com.igurash.igurashwallet.responseandregister.MultiTransactionRequest;
import com.igurash.igurashwallet.responseandregister.TransactionRequest;
import com.igurash.igurashwallet.service.JwtService;
import com.igurash.igurashwallet.service.TransactionService;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class TransactionServiceImpl  implements TransactionService{
   @Autowired
   WalletRepository walletrepo;
   @Autowired
   UserRepository userrepo;
   @Autowired
   JwtService jwtrepo;
   @Autowired 
   TransactionRepository transrepo;
   @Autowired
   ServiceStatusRepository ssrepo;
    @Autowired
     private PasswordEncoder passwordEncoder;
     @Autowired
    private DonationRepositoy drepo;
           

    public String getDonationTransaction(TransactionRequest request , String token ,String pinPassword  ,String deviceId){
        String username = jwtrepo.extractUsernameFromToken(token);
         Optional<User> theuser = userrepo.findByUsername(username);
         Optional<Donation> receiveruser = drepo.findById(request.getReceiver_ID());
         if(theuser.isPresent() && receiveruser.isPresent()){
                  if(passwordEncoder.matches(pinPassword, theuser.get().getWalletPin()))
                      
                  {
                      
                      if(theuser.get().getWalletId() != 0 && receiveruser.get().getWallet_id() !=0 )
                      {
                       if(theuser.get().getWalletId() != receiveruser.get().getWallet_id()){
                           
                              Optional <Wallet> sender = walletrepo.findById(theuser.get().getWalletId());
                          Optional <Wallet> receiver = walletrepo.findById(receiveruser.get().getWallet_id());
                          if(sender.get().getBalance() >= request.getAmount()){
                              double val = sender.get().getBalance()-request.getAmount();
                              sender.get().setBalance(val);
                              receiver.get().setBalance(receiver.get().getBalance()+ request.getAmount());
                              Wallet s1= walletrepo.save(sender.get());
                              Wallet s2= walletrepo.save(receiver.get());
                              
                              if(s1 != null & s2 != null){
                                  Transaction t = new Transaction();
                                  t.setReceiver_ID(s2.getWalletId());
                                  t.setSender_ID(s1.getWalletId());
                                  t.setAmount(request.getAmount());
                                  t.setDatetime( LocalDateTime.now());
                                  t.setTransactionType(request.getTransactionType());
                                  t.setTransactionDeviceID(deviceId);
                                  Transaction newTrans = transrepo.save(t);
                                  if(newTrans != null){
                                      return "Sucessfull transaction "+request.getAmount() ;
                                  }else{
                                      return "Unsuccessful Transaction";
                                  }
                                  
                                  
                              }else{
                                  return "Unsucessful  transaction";
                                  
                              }
                              
                              
                          }else{
                              return "No Sufficient Balance";
                          }
                       }else{
                           return "You cannot load in your own wallet";
                       }
                      }
                      else
                      {
                          return "User is not Wallet User";
                      }
                      
                  }
                  else
                  {
                      return "Incorrect Pin";
                  }
              }
              else
              {
                  return "No user";
              } 
     
    }
    
      @Override
    public String getTransaction(TransactionRequest request,String token,String pinPassword,String deviceId) {
        String username = jwtrepo.extractUsernameFromToken(token);
         Optional<User> receiveruser = userrepo.findByUsername(request.getReceiver_ID());
          Optional<User> theuser = userrepo.findByUsername(username);
        
              if(theuser.isPresent() && receiveruser.isPresent()){
                  if(passwordEncoder.matches(pinPassword, theuser.get().getWalletPin()))
                      
                  {
                      
                      if(theuser.get().getWalletId() != 0 && receiveruser.get().getWalletId() !=0 )
                      {
                       if(theuser.get().getWalletId() != receiveruser.get().getWalletId()){
                           
                              Optional <Wallet> sender = walletrepo.findById(theuser.get().getWalletId());
                          Optional <Wallet> receiver = walletrepo.findById(receiveruser.get().getWalletId());
                          if(sender.get().getBalance() >= request.getAmount()){
                              double val = sender.get().getBalance()-request.getAmount();
                              sender.get().setBalance(val);
                              receiver.get().setBalance(receiver.get().getBalance()+ request.getAmount());
                              Wallet s1= walletrepo.save(sender.get());
                              Wallet s2= walletrepo.save(receiver.get());
                              
                              if(s1 != null & s2 != null){
                                  Transaction t = new Transaction();
                                  t.setReceiver_ID(s2.getWalletId());
                                  t.setSender_ID(s1.getWalletId());
                                  t.setAmount(request.getAmount());
                                  t.setDatetime( LocalDateTime.now());
                                  t.setTransactionType(request.getTransactionType());
                                  t.setTransactionDeviceID(deviceId);
                                  Transaction newTrans = transrepo.save(t);
                                  if(newTrans != null){
                                      return "Sucessfull transaction "+request.getAmount() ;
                                  }else{
                                      return "Unsuccessful Transaction";
                                  }
                                  
                                  
                              }else{
                                  return "Unsucessful  transaction";
                                  
                              }
                              
                              
                          }else{
                              return "No Sufficient Balance";
                          }
                       }else{
                           return "You cannot load in your own wallet";
                       }
                      }
                      else
                      {
                          return "User is not Wallet User";
                      }
                      
                  }
                  else
                  {
                      return "Incorrect Pin";
                  }
              }
              else
              {
                  return "No user";
              } 
          
    }


     @Override
    public String getServiceTransaction(TransactionRequest request, String token, String pinPassword, String deviceId,String prdId,String servietype) {
         String username = jwtrepo.extractUsernameFromToken(token);
         
         Optional<User> receiveruser = userrepo.findByUsername(request.getReceiver_ID());
          Optional<User> theuser = userrepo.findByUsername(username);
        
              if(theuser.isPresent() && receiveruser.isPresent()){
                  if(passwordEncoder.matches(pinPassword, theuser.get().getWalletPin()))
                      
                  {
                      
                      if(theuser.get().getWalletId() != 0 && receiveruser.get().getWalletId() !=0 )
                      {
                           if(theuser.get().getWalletId() != receiveruser.get().getWalletId()){
                           Optional <Wallet> sender = walletrepo.findById(theuser.get().getWalletId());
                          Optional <Wallet> receiver = walletrepo.findById(receiveruser.get().getWalletId());
                          if(sender.get().getBalance() >= request.getAmount()){
                              double val = sender.get().getBalance()-request.getAmount();
                              sender.get().setBalance(val);
                              receiver.get().setBalance(receiver.get().getBalance()+ request.getAmount());
                              Wallet s1= walletrepo.save(sender.get());
                              Wallet s2= walletrepo.save(receiver.get());
                              
                              if(s1 != null & s2 != null){
                                  Transaction t = new Transaction();
                                  t.setReceiver_ID(s2.getWalletId());
                                  t.setSender_ID(s1.getWalletId());
                                  t.setAmount(request.getAmount());
                                  t.setDatetime( LocalDateTime.now());
                                  t.setTransactionType(request.getTransactionType());
                                  t.setTransactionDeviceID(deviceId);
                                  Transaction newTrans = transrepo.save(t);
                                  if(newTrans != null){
                                      Optional<ServiceStatustable> service = ssrepo.findBySenderIdAndProductId(theuser.get().getId(), prdId);
                                  if(service.isPresent()){
                                      service.get().setTrans_id(newTrans.getId());
                                      
                                    ServiceStatustable status = ssrepo.save(service.get());
                                      if(status != null){
                                          if(request.getSenderList() != null){
                                              String s = multiTransaction(request.getSenderList(),request.getReceiver_ID(),request.getAmount());  ;
                                              return s;
                                          }else{
                                             return "Sucessful transaction "+request.getAmount() ; 
                                          }
                                      
                                      }else{
                                          return "Unsuccessful Transaction";
                                      }
                                  }else{
                                      ServiceStatustable snew = new ServiceStatustable();
                                      snew.setSender_id(theuser.get().getId());
                                      snew.setProduct_id(prdId);
                                      snew.setTrans_id(newTrans.getId());
                                      snew.setServiceType(servietype);
                                      ServiceStatustable status = ssrepo.save(snew);
                                      if(status != null){
                                          if( request.getSenderList() != null ){
                                             
                                              String s = multiTransaction(request.getSenderList(),request.getReceiver_ID(),request.getAmount());
                                              
                                          return s;
                                      }else{
                                               return "Sucessful Single  transaction  of "+request.getAmount()  ;
                                          }
                                          
                                     
                                      }else{
                                          return "Unsuccessful Transaction";
                                      }
                                  }
                                  }else{
                                      return "Unsuccessful Transaction";
                                  }
                                  
                                  
                              }else{
                                  return "Unsucessful  transaction";
                                  
                              }
                              
                              
                          }else{
                              return "No Sufficient Balance";
                          }
                           }else{
                               return "No transaction for same user";
                           }
                      
                      }
                      else
                      {
                          return "User is not Wallet User";
                      }
                      
                  }
                  else
                  {
                      return "Incorrect Pin";
                  }
              }
              else
              {
                  return "No user";
              } 
          
}
    

    @Override
    public String getServiceStatus(String token, String prdId) {
        String username = jwtrepo.extractUsernameFromToken(token);
        Optional<User> theuser = userrepo.findByUsername(username);
        Optional<ServiceStatustable> service = ssrepo.findBySenderIdAndProductId(theuser.get().getId(), prdId);
        if(service.isPresent()){
       
        
        if(service.get().getServiceType().equals("Alltimeservice")){
            return "Suscribed";
        }
        else if(service.get().getServiceType().equals("MonthlyService") && service.get().getServiceType().equals("MonthlyAutomatedService") ){
            
          Optional<Transaction>t = transrepo.findById(service.get().getTrans_id());
          LocalDateTime currentDateTime = LocalDateTime.now();
           Duration duration = Duration.between(currentDateTime, t.get().getDatetime());
           if(duration.toDays() <= 30){
               return "Suscribed";
           }else{
               return "Subscribtion Expired";
           }
            
        }
        
        else{
            return "Subscribtion Expired";
        }
            
        }else{
            return "Not Suscribed ";
        }
    }
    public String getAutomatedDonationTransactionService(){
        List<Donation> donationlist = drepo.findAll();
        if(!donationlist.isEmpty()){
            for(Donation donation : donationlist){
               List<Transaction>transactionlist = transrepo.findByReceiver_ID(donation.getWallet_id());
               for(Transaction trans : transactionlist){
                    LocalDateTime currentDateTime = LocalDateTime.now();
                     Optional <Wallet> sender = walletrepo.findById(trans.getReceiver_ID());
           Duration duration = Duration.between(donation.getDatetime(),currentDateTime);
            if(duration.toDays() <=donation.getDays() && sender.get().getBalance() < donation.getTargetAmount()){
               continue;
             }else{
                    Optional <Wallet> receiver = walletrepo.findById(trans.getSender_ID());
              //   Optional <Wallet> sender = walletrepo.findById(trans.getReceiver_ID());
                 sender.get().setBalance(sender.get().getBalance()-trans.getAmount());
                 receiver.get().setBalance(receiver.get().getBalance()+trans.getAmount());
                  Wallet s1= walletrepo.save(sender.get());
                              Wallet s2= walletrepo.save(receiver.get());
                                  if(s1 == null && s2 ==null){
                     continue;
                 }else{
                     Transaction t1 = new Transaction();
                                  t1.setReceiver_ID(s2.getWalletId());
                                  t1.setSender_ID(s1.getWalletId());
                                  t1.setAmount(30);
                                  t1.setDatetime( LocalDateTime.now());
                                  t1.setTransactionType("P2AS");
                                  t1.setTransactionDeviceID("automated service");
                                  Transaction newTrans = transrepo.save(t1);
                                 if(newTrans== null){
                                     return "error Transaction failed";
                                 }else{
                                  
                               
                                       System.out.println("Tansac success");
                                   
                                 }
                                 
                 }
            
            }
              
               }
               
            }
             return "Success";
        }else{
           return "No Services"; 
        }
    }
    public String getAutomatedTransactionService(){
        List<ServiceStatustable> serviceList = ssrepo.findByServiceType("MonthlyAutomatedService");
        if(!serviceList.isEmpty()){
             for (ServiceStatustable service : serviceList) {
            System.out.println("Sender ID"+service.getSender_id());
            Optional<Transaction>t = transrepo.findById(service.getTrans_id());
         //  Optional<Product> prd = prdrepo.findbyProductId(service.getProduct_id();prd.amount
             LocalDateTime currentDateTime = LocalDateTime.now();
           Duration duration = Duration.between(t.get().getDatetime(),currentDateTime);
              System.out.println(duration.toDays());
            if(duration.toDays() <=1){
               continue;
             }else{
                Optional <Wallet> sender = walletrepo.findById(t.get().getSender_ID());
                 Optional <Wallet> receiver = walletrepo.findById(t.get().getReceiver_ID());
            if(sender.get().getBalance() <35){
                continue;
            }
            else{
            
                       
              double val = sender.get().getBalance()-30;//prd.amount
                              sender.get().setBalance(val);
                              receiver.get().setBalance(receiver.get().getBalance()+ 30);
                              Wallet s1= walletrepo.save(sender.get());
                              Wallet s2= walletrepo.save(receiver.get());
                 if(s1 == null && s2 ==null){
                     continue;
                 }else{
                     Transaction t1 = new Transaction();
                                  t1.setReceiver_ID(s2.getWalletId());
                                  t1.setSender_ID(s1.getWalletId());
                                  t1.setAmount(30);
                                  t1.setDatetime( LocalDateTime.now());
                                  t1.setTransactionType("P2AS");
                                  t1.setTransactionDeviceID("automated service");
                                  Transaction newTrans = transrepo.save(t1);
                                 if(newTrans== null){
                                     return "error Transaction failed";
                                 }else{
                                   service.setTrans_id(t1.getId());
                                   ServiceStatustable  status = ssrepo.save(service);
                                   if(status == null){
                                   return "error but transaction is stored ";
                                   }else{
                                       System.out.println(status.getServiceId());
                                   }
                                 }
                                 
                 }
            }
            }
            
             }
            return "Successful";
        }
         else{
        
        return "No Services";
    }
    }
    
    public String multiTransaction(List<MultiTransactionRequest> list,String sender,double amount){
        Optional<User> senderuser = userrepo.findByUsername(sender); 
        if(list.isEmpty()){
            return "No data";
        }else{
            for(MultiTransactionRequest request : list){
                Optional<User> receiveruser = userrepo.findByUsername(request.getId()); 
                if(receiveruser.isPresent() && senderuser.isPresent()){
                    if(receiveruser.get().getWalletId() != 0 && senderuser.get().getWalletId() != 0){
                        if(receiveruser.get().getWalletId() !=senderuser.get().getWalletId() )
                        {
                            Optional <Wallet> senderWallet = walletrepo.findById(senderuser.get().getWalletId());
                 Optional <Wallet> receiver = walletrepo.findById(receiveruser.get().getWalletId());
                            if(senderWallet.isPresent() && receiver.isPresent()){
                                       double val = (amount *  request.getPercentage() )/100  ;
                                       senderWallet.get().setBalance(senderWallet.get().getBalance() - val);
                                       receiver.get().setBalance(receiver.get().getBalance() + val);
                                        Wallet s1= walletrepo.save(senderWallet.get());
                              Wallet s2= walletrepo.save(receiver.get());
                              if(s1 !=null && s2 !=null){
                                     Transaction t = new Transaction();
                                  t.setReceiver_ID(s2.getWalletId());
                                  t.setSender_ID(s1.getWalletId());
                                  t.setAmount(val);
                                  t.setDatetime( LocalDateTime.now());
                                  t.setTransactionType("SP2SP");
                                  t.setTransactionDeviceID("ServicetoOtherPartner");
                                  Transaction newTrans = transrepo.save(t);
                                  if(newTrans != null){
                                      System.out.print( "Sucessfull transaction ") ;
                                  }else{
                                      return "Unsuccessful Transaction";
                                  }
                              }
                            }else{
                                return "failed to load wallet";
                            }
                        }else{
                            return"Same wallet ID";
                        }
                    }else{
                        return "Not a wallet user "+ receiveruser.get().getUsername();
                    }
                    
                }else{
                    return  "No user "+ receiveruser.get().getUsername();
                }
            }
           return "Success of ALl transaction";
        }
        
        
    }
    
    

}