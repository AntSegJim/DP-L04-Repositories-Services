
package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.FixUpTaskRepository;
import security.LoginService;
import security.UserAccount;
import domain.Application;
import domain.Category;
import domain.Customer;
import domain.FixUpTask;
import domain.Warranty;

@Service
@Transactional
public class FixUpTaskService {

	@Autowired
	private FixUpTaskRepository	fixUpTaskRepository;
	@Autowired
	private CustomerService		customerService;
	@Autowired
	private CategoryService		CService;
	@Autowired
	private WarrantyService		WService;


	public FixUpTask create() {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();

		final FixUpTask f = new FixUpTask();
		final Category ca = this.CService.create();
		final Warranty wa = this.WService.create();
		final Customer cus = this.customerService.customerByUserAccount(userAccount.getId());
		f.setAddress("");
		f.setApplication(new HashSet<Application>());
		f.setCategory(ca);
		f.setCustomer(cus);
		f.setDescription("");
		f.setMaximunPrice(0.);
		f.setMoment(new Date());
		f.setPeriodTime(0);
		f.setTicker(FixUpTaskService.generar(new Date()));
		f.setWarranty(wa);
		return f;
	}
	public Collection<FixUpTask> findAll() {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		final Customer c = this.customerService.customerByUserAccount(userAccount.getId());
		return this.fixUpTaskRepository.fixUpTasksCustomer(c.getId());
	}

	public FixUpTask findOne(final Integer id) {
		return this.fixUpTaskRepository.findOne(id);
	}
	public FixUpTask save(final FixUpTask f) {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.isTrue(f.getCustomer().getUserAccount().equals(userAccount));
		Assert.isTrue(f.getTicker() != null && f.getTicker() != "" && f.getMoment() != null && f.getMoment().before(Calendar.getInstance().getTime()) && f.getCategory() != null && f.getCustomer() != null);
		return this.fixUpTaskRepository.save(f);
	}
	public void delete(final FixUpTask f) {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.isTrue(f.getCustomer().getUserAccount().equals(userAccount));
		Assert.isTrue(this.fixUpTaskRepository.findAll().contains(f));
		this.fixUpTaskRepository.delete(f);
	}

	public Collection<FixUpTask> fixUpTasksByFinder(final Integer finderId) {
		return this.fixUpTasksByFinder(finderId);
	}
	public Collection<Double> maxMinAvgDevFixUpTask() {
		final Collection<Double> res = new LinkedList<Double>();
		final Collection<Integer> x = this.fixUpTaskRepository.maxMinAvgDevFixUpTask();
		final List<Integer> lista = (List<Integer>) x;
		res.add(lista.get(lista.size() - 1) * 1.0);
		res.add(lista.get(0) * 1.0);
		Double suma = 0.;
		for (int i = 0; i < lista.size(); i++)
			suma += lista.get(i);
		final Double media = suma / lista.size();
		res.add(media);
		Double sum = 0.;
		for (int i = 0; i < lista.size(); i++)
			sum += (lista.get(i) * lista.get(i)) / (lista.size() - media * media);
		Double dev = 0.;
		dev = Math.sqrt(sum);
		res.add(dev);
		return res;
	}

	public static String generar(final Date date) {
		final int tam = 6;
		final Integer ano = date.getYear() + 1900;
		final Integer mes = date.getMonth() + 1;
		final Integer dia = date.getDate();
		final String d = ano.toString().substring(ano.toString().length() - 2, ano.toString().length()) + mes.toString() + dia.toString();

		String ticker = "-";
		final String a = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

		for (int i = 0; i < tam; i++) {
			final Integer random = (int) (Math.floor(Math.random() * a.length()) % a.length());
			ticker = ticker + a.charAt(random);
		}

		return d + ticker;

	}

}
