import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { PriceFormService, PriceFormGroup } from './price-form.service';
import { IPrice } from '../price.model';
import { PriceService } from '../service/price.service';

@Component({
  selector: 'jhi-price-update',
  templateUrl: './price-update.component.html',
})
export class PriceUpdateComponent implements OnInit {
  isSaving = false;
  price: IPrice | null = null;

  editForm: PriceFormGroup = this.priceFormService.createPriceFormGroup();

  constructor(
    protected priceService: PriceService,
    protected priceFormService: PriceFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ price }) => {
      this.price = price;
      if (price) {
        this.updateForm(price);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const price = this.priceFormService.getPrice(this.editForm);
    if (price.id !== null) {
      this.subscribeToSaveResponse(this.priceService.update(price));
    } else {
      this.subscribeToSaveResponse(this.priceService.create(price));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPrice>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(price: IPrice): void {
    this.price = price;
    this.priceFormService.resetForm(this.editForm, price);
  }
}
