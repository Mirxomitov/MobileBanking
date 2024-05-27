package uz.gita.mobilebanking.data.source.local

import uz.gita.mobilebanking.data.model.CardData
import uz.gita.mobilebanking.data.model.PayedCardData
import uz.gita.mobilebanking.data.model.TemplateCardData
import uz.gita.mobilebanking.data.source.local.entity.CardEntity
import uz.gita.mobilebanking.data.source.local.entity.PayedCardEntity
import uz.gita.mobilebanking.data.source.local.entity.TemplateCardEntity

fun PayedCardEntity.toPayedCardData() = PayedCardData(pan = pan, ownerName = ownerName)
fun PayedCardData.toEntity() = PayedCardEntity(pan = pan, ownerName = ownerName)
fun TemplateCardData.toEntity() = TemplateCardEntity(pan = pan, ownerName = ownerName);
fun TemplateCardEntity.toCardData() = TemplateCardData(pan = pan, ownerName = ownerName);
fun CardEntity.toCardData() = CardData(id = id.toInt(), name = name, amount = amount, owner = owner, pan = pan)
